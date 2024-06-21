package converter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.mock.Expectation;
import org.mockserver.model.HttpForward;
import org.mockserver.model.HttpRequest;
import org.mockserver.serialization.ObjectMapperFactory;
import org.mockserver.serialization.model.ExpectationDTO;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import mockserver.InjectMockServer;
import mockserver.MockServerProxy;

@QuarkusTest
@QuarkusTestResource(MockServerProxy.class)
class PersonTest {

	private static final Path MOCK_SERVER_EXPECTATIONS_ROOT = Paths.get("src/test/resources/mock-server");

	@InjectMockServer
	ClientAndServer mockServer;

	@Inject
	PersonService service;

	@BeforeEach
	void init(TestInfo info) throws IOException {
		mockServer.reset();

		if(mockserverForwardToRealServer()) {
			mockServer.when(
					HttpRequest.request()
			)
			.forward(
					HttpForward.forward()
					.withHost("swapi.dev")
			);
		} else {
			if(Files.isDirectory(MOCK_SERVER_EXPECTATIONS_ROOT)) {
				ObjectMapper objectMapper = ObjectMapperFactory.createObjectMapper();
				String testMethodName = getTestMethodName(info);
				Files.list(MOCK_SERVER_EXPECTATIONS_ROOT)
					.filter(p -> p.getFileName().toString().startsWith(testMethodName + "_"))
					.sorted()
					.map(p -> readExpectation(objectMapper, p))
					.forEach(e -> mockServer.upsert(e));
			} else {
				System.out.println("No expectactions found, because " + MOCK_SERVER_EXPECTATIONS_ROOT.toAbsolutePath() + " is not a directory");
			}
		}
	}

	private boolean mockserverForwardToRealServer() {
		String value = System.getenv("MOCKSERVER_FORWARD_TO_REAL_SERVER");
		return value != null && value.toLowerCase().equals("true");
	}

	private Expectation readExpectation(ObjectMapper objectMapper, Path file){
		try {
			String content = Files.readString(file);
			ExpectationDTO dto = objectMapper.readValue(content, ExpectationDTO.class);
			return dto.buildObject();
		} catch (IOException e) {
			throw new IllegalStateException("Could not read the expectation file " + file, e);
		}
	}
	
	@AfterEach
	void recordExpectations(TestInfo info) throws IOException {
		if(mockserverForwardToRealServer()) {
			Expectation[] list = mockServer.retrieveRecordedExpectations(HttpRequest.request());
			String testMethodName = getTestMethodName(info);

			Files.createDirectories(MOCK_SERVER_EXPECTATIONS_ROOT);
			for (int i = 0; i < list.length; i++) {
				ExpectationDTO item = new ExpectationDTO(list[i]);
				Path file = MOCK_SERVER_EXPECTATIONS_ROOT.resolve(testMethodName+ "_" + String.format("%03d", i + 1) + ".json");
				Files.writeString(file, item.toString());
			}
		}
	}

	private String getTestMethodName(TestInfo info) {
		String testMethodName = info.getTestMethod()
				.map(Method::getName)
				.orElseThrow(() -> new IllegalStateException("Could not find the test method name"));
		return testMethodName;
	}
	
	@Test
	void test1() throws Exception {
		Person result = service.getPerson(1);
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.name).isEqualTo("Luke Skywalker");
	}
	@Test
	void test2() throws Exception {
		Person result = service.getPerson(2);
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.name).isEqualTo("C-3PO");
	}

}

package mockserver;


import java.util.Collections;
import java.util.Map;

import org.mockserver.integration.ClientAndServer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class MockServerProxy implements QuarkusTestResourceLifecycleManager {

	ClientAndServer mockServer;

	@Override
	public Map<String, String> start() {
		mockServer = ClientAndServer.startClientAndServer(9999);

		System.out.println("Mock server running on port " + mockServer.getLocalPort());
		return Collections.singletonMap("quarkus.rest-client.\"swapi.SwapiService\".url", "http://localhost:" + mockServer.getLocalPort());
	}

	@Override
	public synchronized void stop() {
		if (mockServer != null) {
			mockServer.stop();
			mockServer = null;
		}
	}

	@Override
	public void inject(TestInjector testInjector) {
		testInjector.injectIntoFields(
				mockServer,
				new TestInjector.AnnotatedAndMatchesType(InjectMockServer.class, ClientAndServer.class));
	}

}

package fr.jmini.quarkuscli;

import java.net.URI;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import fr.jmini.quarkuscli.sonar.ProjectStatusResponse;
import fr.jmini.quarkuscli.sonar.PullRequestsResponse;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "greeting", mixinStandardHelpOptions = true)
public class GreetingCommand implements Runnable {

	@ConfigProperty(name = "sonarqube.host", defaultValue = "https://sonarcloud.io")
	String sonarHost;

	@Option(names = "--projectKey", description = "Sonar project key")
	String projectKey;

	@Option(names = "--pullRequest", description = "Sonar pull request")
	String pullRequest;

	@Parameters(paramLabel = "<name>", defaultValue = "picocli", description = "Your name.")
	String name;

	@Override
	public void run() {
		System.out.printf("Hello %s, go go commando!\n", name);

		MyRemoteService sonarClient = RestClientBuilder.newBuilder()
				.baseUri(URI.create(sonarHost))
				.build(MyRemoteService.class);

		ProjectStatusResponse projectStatus = sonarClient.getProjectStatus(projectKey, pullRequest);
		System.out.println("projectStatus: " + projectStatus);

		PullRequestsResponse list = sonarClient.listPullRequests(projectKey);
		System.out.println("list:" + list);
	}

}

package org.acme.app1;

import org.acme.core.CoreProcessor;
import org.acme.core.kt.ConfigService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class App1Controller {

	@Inject
	private CoreProcessor processor;

	@Inject
	private ConfigService config;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return config.getHeader() + ":\n" + String.join("\n", processor.getAllMessages()) + "\n" + "-- end";
	}
}

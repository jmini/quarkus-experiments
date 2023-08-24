package org.acme.app1;

import org.acme.core.CoreProcessor;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class App1Controller {

	@Inject
	private CoreProcessor processor;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Messages:\n" + String.join("\n", processor.getAllMessages()) + "\n" + "-- end";
	}
}

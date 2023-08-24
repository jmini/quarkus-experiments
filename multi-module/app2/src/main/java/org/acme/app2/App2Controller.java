package org.acme.app2;

import static java.util.Objects.requireNonNull;

import org.acme.core.CoreProcessor;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class App2Controller {

	private final Template page;

	@Inject
	private CoreProcessor processor;

	public App2Controller(Template page) {
		this.page = requireNonNull(page, "page is required");
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public TemplateInstance get() {
		return page.data("messages", processor.getAllMessages());
	}

}

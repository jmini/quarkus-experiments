package org.acme.app2;

import org.acme.core.CoreMessage;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class App2OtherMessage implements CoreMessage {

	public String getMessage() {
		return "Other message (App 2)";
	}
}

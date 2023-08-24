package org.acme.app1;

import org.acme.core.CoreMessage;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class App1Message implements CoreMessage {

	public String getMessage() {
		return "This is app 1";
	}
}

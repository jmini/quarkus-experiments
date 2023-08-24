package org.acme.app2;

import org.acme.core.CoreMessage;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class App2Message implements CoreMessage {

	public String getMessage() {
		return "This is app 2";
	}
}

package org.acme.core;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class TimestampMessage implements CoreMessage {

	@Override
	public String getMessage() {
		return "Timestamp " + System.currentTimeMillis();
	}
}
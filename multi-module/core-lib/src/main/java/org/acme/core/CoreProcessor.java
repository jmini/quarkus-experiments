package org.acme.core;

import java.util.List;

import io.quarkus.arc.All;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CoreProcessor {

	@Inject
	@All
	List<CoreMessage> messsages;

	public List<String> getAllMessages() {
		return messsages.stream()
				.map(CoreMessage::getMessage)
				.sorted()
				.toList();
	}

}

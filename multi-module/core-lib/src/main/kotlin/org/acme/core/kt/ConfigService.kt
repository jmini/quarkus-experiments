package org.acme.core.kt

import org.eclipse.microprofile.config.inject.ConfigProperty

import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
open class ConfigService(
    @ConfigProperty(name = "config.message.header", defaultValue = "Undefined") val message: String
) {
	fun getHeader(): String {
	    return message
    }
}

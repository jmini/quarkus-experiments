package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.quarkus.logging.Log;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        long timestamp = System.currentTimeMillis();
        Log.trace("This is TRACE log " + timestamp);
        Log.info("This is INFO log " + timestamp);
        Log.warn("This is WARN log " + timestamp);
        Log.error("This is ERROR log " + timestamp);
        return "Hello from Quarkus REST " + timestamp;
    }
}

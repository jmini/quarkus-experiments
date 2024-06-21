package swapi;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@RegisterRestClient
public interface SwapiService {

    @GET
    @Path("/api/people/{id}")
    SwapiPeople getPeopleById(@PathParam("id") String id);
}

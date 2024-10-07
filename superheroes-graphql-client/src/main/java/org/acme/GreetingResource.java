package org.acme;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import superheroes.api.SuperheroesClientApi;
import superheroes.model.City;
import superheroes.model.CityInput;
import superheroes.model.Superhero;
import superheroes.model.SuperheroInput;

@Path("")
public class GreetingResource {

    @Inject
    SuperheroesClientApi api;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/readCities")
    @Produces(MediaType.TEXT_PLAIN)
    public String readCities() {
        List<City> response = api.allCities();
        return "Read Response " + response;
    }

    @GET
    @Path("/readCity/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String readCity(@PathParam("id") Integer id) {
        City response = api.city(id);
        return "Response to string " + response;
    }

    @GET
    @Path("/readSuperheroes")
    @Produces(MediaType.TEXT_PLAIN)
    public String readSuperheroes() {
        List<Superhero> response = api.allSuperheroes();
        return "Read Response: " + response;
    }

    @GET
    @Path("/readSuperhero/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String readSuperhero(@PathParam("id") Integer id) {
        Superhero response = api.superhero(id);
        return "Read Response: " + response;
    }

    @GET
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    public String create() {
        SuperheroInput input = new SuperheroInput();
        input.setName("H" + System.currentTimeMillis());
        CityInput cityInput = new CityInput();
        cityInput.setName("C" + System.currentTimeMillis());
        input.setCity(cityInput);
        api.createSuperhero(input);
        List<Superhero> response = api.allSuperheroes();
        return "Create Response: " + response;
    }
}

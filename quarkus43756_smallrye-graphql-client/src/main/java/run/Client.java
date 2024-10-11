package run;

import java.util.List;

import org.jboss.logging.Logger;

import io.smallrye.graphql.client.typesafe.api.TypesafeGraphQLClientBuilder;
import superheroes.api.SuperheroesClientApi;
import superheroes.model.City;
import superheroes.model.Superhero;

public class Client {
	private static final Logger LOGGER = Logger.getLogger(Client.class);
	
    public static void main(String... args) throws Exception {
        LOGGER.info("--- Start ---");
        LOGGER.debug("Timestamp: " + System.currentTimeMillis());

        SuperheroesClientApi api = TypesafeGraphQLClientBuilder.newBuilder().build(SuperheroesClientApi.class);

        List<City> allCities = api.allCities();
        System.out.println("response: " + allCities);
        
        List<Superhero> allSuperheroes = api.allSuperheroes();
        System.out.println("response: " + allSuperheroes);

        LOGGER.info("--- End ---");
    }
}
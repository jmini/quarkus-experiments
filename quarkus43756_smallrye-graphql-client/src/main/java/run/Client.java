package run;

import java.util.List;

import io.smallrye.graphql.client.typesafe.api.TypesafeGraphQLClientBuilder;
import superheroes.api.SuperheroesClientApi;
import superheroes.model.City;
import superheroes.model.Superhero;

public class Client {
    public static void main(String... args) throws Exception {
        
        SuperheroesClientApi api = TypesafeGraphQLClientBuilder.newBuilder().build(SuperheroesClientApi.class);

        List<City> allCities = api.allCities();
        System.out.println("response: " + allCities);
        
        List<Superhero> allSuperheroes = api.allSuperheroes();
        System.out.println("response: " + allSuperheroes);
        
    }
}
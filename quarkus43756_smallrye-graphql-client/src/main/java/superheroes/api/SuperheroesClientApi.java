package superheroes.api;

import java.util.List;

import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

import io.smallrye.graphql.client.typesafe.api.GraphQLClientApi;
import superheroes.model.City;
import superheroes.model.Superhero;
import superheroes.model.SuperheroInput;

@GraphQLClientApi(configKey = "superheroes", endpoint = "http://localhost:8080/graphql")
public interface SuperheroesClientApi {

    /**
     * Get all cities.
     */
    @Query("allCities")
    List<City> allCities();

    /**
     * Get all superheroes.
     */
    @Query("allSuperheroes")
    List<Superhero> allSuperheroes();

    /**
     * Get a City.
     */
    @Query("city")
    City city(@NonNull Integer cityId);

    /**
     * Get a super hero.
     */
    @Query("superhero")
    Superhero superhero(@NonNull Integer heroId);

    @Mutation("createSuperhero")
    Superhero createSuperhero(@Source SuperheroInput superhero);

}

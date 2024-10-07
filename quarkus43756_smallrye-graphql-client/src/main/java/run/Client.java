package run;

import java.util.List;

import io.smallrye.graphql.client.Response;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;
import io.smallrye.graphql.client.typesafe.api.TypesafeGraphQLClientBuilder;
import io.smallrye.graphql.client.vertx.dynamic.VertxDynamicGraphQLClientBuilder;
import io.vertx.core.Vertx;
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
        
//        Vertx vertx = Vertx.vertx();
//        DynamicGraphQLClient client = new VertxDynamicGraphQLClientBuilder()
//            .url("https://countries.trevorblades.com")
//            .vertx(vertx)
//            .build();
//        try {
//            Response response = client.executeSync("""
//                query {
//                  countries {
//                    name
//                  }
//                }
//                """);
//            System.out.println(response);
//        } finally {
//            client.close();
//            vertx.close();
//        }
    }
}
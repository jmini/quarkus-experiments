package org.acme.rest.json;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import io.quarkus.vertx.web.Body;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.Route.HttpMethod;
import io.quarkus.vertx.web.RouteBase;

@RouteBase(path = "fruits")
public class FruitResource {

    private Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public FruitResource() {
        fruits.add(new Fruit("Apple", "Winter fruit"));
        fruits.add(new Fruit("Pineapple", "Tropical fruit"));
    }

    @Route(path="", methods = HttpMethod.GET)
    public Set<Fruit> list() {
        return fruits;
    }

    @Route(path="", methods = HttpMethod.POST)
    public Set<Fruit> add(@Body Fruit fruit) {
        fruits.add(fruit);
        return fruits;
    }

    @Route(path="", methods = HttpMethod.DELETE)
    public Set<Fruit> delete(@Body Fruit fruit) {
        fruits.removeIf(existingFruit -> existingFruit.name.contentEquals(fruit.name));
        return fruits;
    }
}

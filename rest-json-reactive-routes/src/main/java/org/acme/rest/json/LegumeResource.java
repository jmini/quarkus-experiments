package org.acme.rest.json;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.Route.HttpMethod;
import io.quarkus.vertx.web.RouteBase;
import io.quarkus.vertx.web.RoutingExchange;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.Json;

@RouteBase(path = "legumes")
public class LegumeResource {

    private Set<Legume> legumes = Collections.synchronizedSet(new LinkedHashSet<>());

    public LegumeResource() {
        legumes.add(new Legume("Carrot", "Root vegetable, usually orange"));
        legumes.add(new Legume("Zucchini", "Summer squash"));
    }

    @Route(path="", methods = HttpMethod.GET)
    public void list(RoutingExchange re) {
        String body = Json.encode(legumes);
        re.ok()
            .putHeader(HttpHeaders.CONTENT_TYPE.toString(), "application/json")
            .end(body);
    }
}

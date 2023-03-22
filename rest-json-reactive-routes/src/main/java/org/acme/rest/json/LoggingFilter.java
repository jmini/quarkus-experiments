package org.acme.rest.json;

import org.jboss.logging.Logger;

import io.quarkus.vertx.web.RouteFilter;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

public class LoggingFilter {

    private static final Logger LOG = Logger.getLogger(LoggingFilter.class);

    @RouteFilter(100) 
    void myFilter(RoutingContext rc) {
       final HttpMethod method = rc.request().method();
       final String path = rc.request().path();
       final String address = rc.request().remoteAddress().toString();

       LOG.infof("Request %s %s from IP %s", method, path, address);
       rc.next(); 
    }
}

# code-with-quarkus

Reproducer for https://github.com/quarkusio/quarkus/issues/36024

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

To trigger the exception, run:

```
curl http://localhost:8080/hello
```

The call is never returning.
On the server side you have following stacktrace:

```
java.lang.ClassCastException: class org.glassfish.jersey.message.internal.OutboundJaxrsResponse$Builder cannot be cast to class org.jboss.resteasy.reactive.server.jaxrs.ResponseBuilderImpl (org.glassfish.jersey.message.internal.OutboundJaxrsResponse$Builder and org.jboss.resteasy.reactive.server.jaxrs.ResponseBuilderImpl are in unnamed module of loader io.quarkus.bootstrap.classloading.QuarkusClassLoader @aecb35a)
        at org.jboss.resteasy.reactive.server.handlers.ResponseHandler$1.get(ResponseHandler.java:155)
        at org.jboss.resteasy.reactive.server.core.ServerSerialisers.encodeResponseHeaders(ServerSerialisers.java:503)
        at org.jboss.resteasy.reactive.server.core.ServerSerialisers$1.accept(ServerSerialisers.java:77)
        at org.jboss.resteasy.reactive.server.core.ServerSerialisers$1.accept(ServerSerialisers.java:74)
        at org.jboss.resteasy.reactive.server.vertx.VertxResteasyReactiveRequestContext.handle(VertxResteasyReactiveRequestContext.java:468)
        at org.jboss.resteasy.reactive.server.vertx.VertxResteasyReactiveRequestContext.handle(VertxResteasyReactiveRequestContext.java:45)
        at io.vertx.ext.web.impl.RoutingContextImpl.lambda$null$0(RoutingContextImpl.java:495)
        at io.vertx.ext.web.impl.SparseArray.forEachInReverseOrder(SparseArray.java:41)
        at io.vertx.ext.web.impl.RoutingContextImpl.lambda$getHeadersEndHandlers$1(RoutingContextImpl.java:495)
        at io.vertx.core.http.impl.Http1xServerResponse.prepareHeaders(Http1xServerResponse.java:732)
        at io.vertx.core.http.impl.Http1xServerResponse.end(Http1xServerResponse.java:430)
        at io.vertx.core.http.impl.Http1xServerResponse.end(Http1xServerResponse.java:415)
        at org.jboss.resteasy.reactive.server.vertx.VertxResteasyReactiveRequestContext.end(VertxResteasyReactiveRequestContext.java:370)
        at org.jboss.resteasy.reactive.server.providers.serialisers.ServerStringMessageBodyHandler.writeResponse(ServerStringMessageBodyHandler.java:26)
        at org.jboss.resteasy.reactive.server.core.ServerSerialisers.invokeWriter(ServerSerialisers.java:227)
        at org.jboss.resteasy.reactive.server.core.ServerSerialisers.invokeWriter(ServerSerialisers.java:195)
        at org.jboss.resteasy.reactive.server.core.serialization.FixedEntityWriter.write(FixedEntityWriter.java:28)
        at org.jboss.resteasy.reactive.server.handlers.ResponseWriterHandler.handle(ResponseWriterHandler.java:34)
        at io.quarkus.resteasy.reactive.server.runtime.QuarkusResteasyReactiveRequestContext.invokeHandler(QuarkusResteasyReactiveRequestContext.java:147)
        at org.jboss.resteasy.reactive.common.core.AbstractResteasyReactiveContext.run(AbstractResteasyReactiveContext.java:147)
        at io.quarkus.vertx.core.runtime.VertxCoreRecorder$14.runWith(VertxCoreRecorder.java:577)
        at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2513)
        at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1538)
        at org.jboss.threads.DelegatingRunnable.run(DelegatingRunnable.java:29)
        at org.jboss.threads.ThreadLocalResettingRunnable.run(ThreadLocalResettingRunnable.java:29)
        at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
        at java.base/java.lang.Thread.run(Thread.java:833)
```

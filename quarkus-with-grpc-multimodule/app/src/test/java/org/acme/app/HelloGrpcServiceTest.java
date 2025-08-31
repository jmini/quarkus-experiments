package org.acme.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.acme.model.HelloGrpc;
import org.acme.model.HelloRequest;
import org.acme.model.HelloReply;

import java.time.Duration;

import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

@QuarkusTest
class HelloGrpcServiceTest {
    @GrpcClient
    HelloGrpc helloGrpc;

    @Test
    void testHello() {
        HelloReply reply = helloGrpc
                .sayHello(HelloRequest.newBuilder().setName("Neo").build()).await().atMost(Duration.ofSeconds(5));
        assertEquals("Hello Neo!", reply.getMessage());
    }

}

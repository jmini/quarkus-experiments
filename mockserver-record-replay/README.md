# mockserver-record-replay

This small quarkus app is experimenting with the idea of mocking a thirdparty sever at HTTP level during tests.
This the approach described in the [quarkus rest client guide](https://quarkus.io/guides/rest-client#using-a-mock-http-server-for-tests).

Instead of Wiremock the project used is https://mock-server.com/.

In order to not have to write the mock responses, the mock-server can be used in two modes.

When the environement variable `MOCKSERVER_FORWARD_TO_REAL_SERVER` is set to `true`, the requests are forwarded to the real server and the responses are captured and stored in the `mock-server` folder.

By default the server is just replaying recorded requests.

This approach is very usefull to not have to write the responses corrresponding to the requests used during tests manually.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.


## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.


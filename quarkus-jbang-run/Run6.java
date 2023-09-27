///usr/bin/env jbang "$0" "$@" ; exit $?

// PREREQUISITE:
// Clone https://github.com/jmini/quarkus-picocli-experiment/
// Checkout branch "maven-publish"
// Run: '/.gradlew publishToMavenLocal'
//
// This should create this files:
// ~/.m2/repository/org/acme/quarkus-picocli-experiment/1.0.0-SNAPSHOT/quarkus-picocli-experiment-1.0.0-SNAPSHOT.pom
// ~/.m2/repository/org/acme/quarkus-picocli-experiment/1.0.0-SNAPSHOT/quarkus-picocli-experiment-1.0.0-SNAPSHOT.jar

//DEPS org.acme:quarkus-picocli-experiment:1.0.0-SNAPSHOT
//JAVA 17

public class Run6 {

    public static void main(String... args) throws Throwable {
        io.quarkus.runtime.Quarkus.run(args);
    }
}
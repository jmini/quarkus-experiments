///usr/bin/env jbang "$0" "$@" ; exit $?

//REPOS mavencentral,jitpack=https://jitpack.io
//DEPS com.github.jmini:quarkus-picocli-experiment:main-SNAPSHOT
//JAVA 17

public class Run5 {

    public static void main(String... args) throws Throwable {
        io.quarkus.runtime.Quarkus.run(args);
    }
}

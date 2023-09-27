///usr/bin/env jbang "$0" "$@" ; exit $?

//DEPS https://github.com/jmini/quarkus-picocli-experiment/tree/main#:SNAPSHOT
//JAVA 17

public class Run2 {

    public static void main(String... args) throws Throwable {
        io.quarkus.bootstrap.runner.QuarkusEntryPoint.main(args);
    }
}

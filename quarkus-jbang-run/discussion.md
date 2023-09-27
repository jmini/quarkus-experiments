I have a quarkus picocli application.

The discussion is about running just the published `jar` not the `uber-jar` variant.

Is there a way to run this quarkus CLI application directly with Jbang?

Obviously I am missing something...

**Setup:**

Link to generate the app: [code.quarkus.io](https://code.quarkus.io/?a=quarkus-picocli-experiment&b=GRADLE&e=picocli)

This is pushed to this repo:
https://github.com/jmini/quarkus-picocli-experiment

It can be built with Jitpack ([build log](https://jitpack.io/com/github/jmini/quarkus-picocli-experiment/main-9fc985be83-1/build.log)), so it is available on a maven repository:

<details><summary>Maven coordinates</summary>
<p>

using:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

as dependency:

```xml
<dependency>
    <groupId>com.github.jmini</groupId>
    <artifactId>quarkus-picocli-experiment</artifactId>
    <version>main-SNAPSHOT</version>
</dependency>
```

</p>
</details> 

<details><summary>Gradle coordinates</summary>
<p>

using:
```gradle
repositories {
    maven { url 'https://jitpack.io' }
}
```

```gradle
dependencies {
	implementation 'com.github.jmini:quarkus-picocli-experiment:main-SNAPSHOT'
}
```
</p>
</details> 

Usage of JitPack is easier for this discussion, but I could imagine working with a regular maven repo (inside our company repo or maven central)

**Experiments:**

_Test 1_

I have tried:
```
jbang run --repos=https://jitpack.io com.github.jmini:quarkus-picocli-experiment:main-SNAPSHOT
```

Output
```
[jbang] [ERROR] no main class deduced, specified nor found in a manifest
[jbang] Run with --verbose for more details
```

Somehow expected when you look at the content of [quarkus-picocli-experiment-main-9fc985be83-1.jar](https://jitpack.io/com/github/jmini/quarkus-picocli-experiment/main-9fc985be83-1/quarkus-picocli-experiment-main-9fc985be83-1.jar) (it is not a  standalone jar) 

_Test 2_

```java
///usr/bin/env jbang "$0" "$@" ; exit $?

//DEPS https://github.com/jmini/quarkus-picocli-experiment/tree/main#:SNAPSHOT
//JAVA 17

public class Run2 {

    public static void main(String... args) throws Throwable {
        io.quarkus.bootstrap.runner.QuarkusEntryPoint.main(args);
    }
}
```

_Test 3_

```java
///usr/bin/env jbang "$0" "$@" ; exit $?

//DEPS https://github.com/jmini/quarkus-picocli-experiment/tree/main#:SNAPSHOT
//JAVA 17

public class Run3 {

    public static void main(String... args) throws Throwable {
        io.quarkus.runtime.Quarkus.run(args);
    }
}
```

The Stacktrace when using `--verbose` for Run2 and Run3 looks similar and seems to be related to the JitPack repo not being declared to the postBuild step. (maybe a first issue?)

<details><summary>Stacktrace for Run2 and Run3</summary>
<p>

```
[jbang] [3:611] [ERROR] Issue running postBuild()
dev.jbang.cli.ExitException: Issue running postBuild()
	at dev.jbang.spi.IntegrationManager.runIntegrations(IntegrationManager.java:111)
	at dev.jbang.source.buildsteps.IntegrationBuildStep.build(IntegrationBuildStep.java:35)
	at dev.jbang.source.buildsteps.IntegrationBuildStep.build(IntegrationBuildStep.java:19)
	at dev.jbang.source.AppBuilder.build(AppBuilder.java:98)
	at dev.jbang.source.AppBuilder.build(AppBuilder.java:22)
	at dev.jbang.cli.Run.doCall(Run.java:89)
	at dev.jbang.cli.BaseCommand.call(BaseCommand.java:145)
	at dev.jbang.cli.BaseCommand.call(BaseCommand.java:21)
	at picocli.CommandLine.executeUserObject(CommandLine.java:1953)
	at picocli.CommandLine.access$1300(CommandLine.java:145)
	at picocli.CommandLine$RunLast.executeUserObjectOfLastSubcommandWithSameParent(CommandLine.java:2358)
	at picocli.CommandLine$RunLast.handle(CommandLine.java:2352)
	at dev.jbang.cli.JBang$3.handle(JBang.java:148)
	at dev.jbang.cli.JBang$3.handle(JBang.java:143)
	at picocli.CommandLine$AbstractParseResultHandler.execute(CommandLine.java:2179)
	at picocli.CommandLine$RunLast.execute(CommandLine.java:2316)
	at picocli.CommandLine.execute(CommandLine.java:2078)
	at dev.jbang.Main.main(Main.java:14)
Caused by: java.lang.reflect.InvocationTargetException
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at dev.jbang.spi.IntegrationManager.runIntegrationEmbedded(IntegrationManager.java:182)
	at dev.jbang.spi.IntegrationManager.runIntegrations(IntegrationManager.java:99)
	... 17 more
Caused by: java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
	at io.quarkus.launcher.JBangIntegration.postBuild(JBangIntegration.java:128)
	... 23 more
Caused by: java.lang.reflect.InvocationTargetException
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at io.quarkus.launcher.JBangIntegration.postBuild(JBangIntegration.java:120)
	... 23 more
Caused by: java.lang.RuntimeException: io.quarkus.bootstrap.BootstrapException: Failed to create the application model for dev.jbang.user:quarkus::jar:999-SNAPSHOT[paths: /Users/jbr/.jbang/cache/jars/Run3.java.f54ab8bd2139bcb7cdcccd2e7869879bda76c0f27229aca98bf5d1ca81db9238/classes;]
	at io.quarkus.bootstrap.jbang.JBangBuilderImpl.postBuild(JBangBuilderImpl.java:95)
	... 28 more
Caused by: io.quarkus.bootstrap.BootstrapException: Failed to create the application model for dev.jbang.user:quarkus::jar:999-SNAPSHOT[paths: /Users/jbr/.jbang/cache/jars/Run3.java.f54ab8bd2139bcb7cdcccd2e7869879bda76c0f27229aca98bf5d1ca81db9238/classes;]
	at io.quarkus.bootstrap.BootstrapAppModelFactory.resolveAppModel(BootstrapAppModelFactory.java:297)
	at io.quarkus.bootstrap.app.QuarkusBootstrap.bootstrap(QuarkusBootstrap.java:133)
	at io.quarkus.bootstrap.jbang.JBangBuilderImpl.postBuild(JBangBuilderImpl.java:86)
	... 28 more
Caused by: io.quarkus.bootstrap.resolver.maven.BootstrapMavenException: Failed to resolve dependencies for dev.jbang.user:quarkus:jar:999-SNAPSHOT
	at io.quarkus.bootstrap.resolver.maven.ApplicationDependencyTreeResolver.resolveRuntimeDeps(ApplicationDependencyTreeResolver.java:315)
	at io.quarkus.bootstrap.resolver.maven.ApplicationDependencyTreeResolver.resolve(ApplicationDependencyTreeResolver.java:135)
	at io.quarkus.bootstrap.resolver.BootstrapAppModelResolver.buildAppModel(BootstrapAppModelResolver.java:321)
	at io.quarkus.bootstrap.resolver.BootstrapAppModelResolver.doResolveModel(BootstrapAppModelResolver.java:286)
	at io.quarkus.bootstrap.resolver.BootstrapAppModelResolver.resolveManagedModel(BootstrapAppModelResolver.java:165)
	at io.quarkus.bootstrap.BootstrapAppModelFactory.resolveAppModel(BootstrapAppModelFactory.java:283)
	... 30 more
Caused by: org.eclipse.aether.resolution.DependencyResolutionException: The following artifacts could not be resolved: com.github.jmini:quarkus-picocli-experiment:jar:main-9fc985be83-1 (present, but unavailable): Could not find artifact com.github.jmini:quarkus-picocli-experiment:jar:main-9fc985be83-1 in central (https://repo.maven.apache.org/maven2)
	at org.eclipse.aether.internal.impl.DefaultRepositorySystem.resolveDependencies(DefaultRepositorySystem.java:364)
	at io.quarkus.bootstrap.resolver.maven.ApplicationDependencyTreeResolver.resolveRuntimeDeps(ApplicationDependencyTreeResolver.java:311)
	... 35 more
Caused by: org.eclipse.aether.resolution.ArtifactResolutionException: The following artifacts could not be resolved: com.github.jmini:quarkus-picocli-experiment:jar:main-9fc985be83-1 (present, but unavailable): Could not find artifact com.github.jmini:quarkus-picocli-experiment:jar:main-9fc985be83-1 in central (https://repo.maven.apache.org/maven2)
	at org.eclipse.aether.internal.impl.DefaultArtifactResolver.resolve(DefaultArtifactResolver.java:459)
	at org.eclipse.aether.internal.impl.DefaultArtifactResolver.resolveArtifacts(DefaultArtifactResolver.java:259)
	at org.eclipse.aether.internal.impl.DefaultRepositorySystem.resolveDependencies(DefaultRepositorySystem.java:352)
	... 36 more
Caused by: org.eclipse.aether.transfer.ArtifactNotFoundException: Could not find artifact com.github.jmini:quarkus-picocli-experiment:jar:main-9fc985be83-1 in central (https://repo.maven.apache.org/maven2)
	at org.eclipse.aether.connector.basic.ArtifactTransportListener.transferFailed(ArtifactTransportListener.java:42)
	at org.eclipse.aether.connector.basic.BasicRepositoryConnector$TaskRunner.run(BasicRepositoryConnector.java:417)
	at org.eclipse.aether.connector.basic.BasicRepositoryConnector.get(BasicRepositoryConnector.java:260)
	at org.eclipse.aether.internal.impl.DefaultArtifactResolver.performDownloads(DefaultArtifactResolver.java:522)
	at org.eclipse.aether.internal.impl.DefaultArtifactResolver.resolve(DefaultArtifactResolver.java:435)
	... 38 more
[jbang] [3:614] If you believe this a bug in jbang, open an issue at https://github.com/jbangdev/jbang/issues
```

</p>
</details> 


_Test 4_

With an explicit declaration of the jitpack repo:

```java
///usr/bin/env jbang "$0" "$@" ; exit $?

//REPOS mavencentral,jitpack=https://jitpack.io
//DEPS com.github.jmini:quarkus-picocli-experiment:main-SNAPSHOT
//JAVA 17

import static java.lang.System.*;

public class Run4 {

    public static void main(String... args) throws Throwable {
        io.quarkus.bootstrap.runner.QuarkusEntryPoint.main(args);
    }
}
```


Output:

```
[jbang] Resolving dependencies...
[jbang]    com.github.jmini:quarkus-picocli-experiment:main-SNAPSHOT
[jbang] Dependencies resolved
[jbang] Building jar for Run4.java...
[jbang] Post build with io.quarkus.launcher.JBangIntegration
Sep 27, 2023 6:44:06 AM org.jboss.threads.Version <clinit>
INFO: JBoss Threads version 3.5.0.Final
Sep 27, 2023 6:44:07 AM io.quarkus.deployment.QuarkusAugmentor run
INFO: Quarkus augmentation completed in 955ms
Exception in thread "main" java.nio.file.NoSuchFileException: <home>/.m2/repository/io/quarkus/quarkus/quarkus-application.dat
	at java.base/sun.nio.fs.UnixException.translateToIOException(UnixException.java:92)
	at java.base/sun.nio.fs.UnixException.rethrowAsIOException(UnixException.java:106)
	at java.base/sun.nio.fs.UnixException.rethrowAsIOException(UnixException.java:111)
	at java.base/sun.nio.fs.UnixFileSystemProvider.newByteChannel(UnixFileSystemProvider.java:218)
	at java.base/java.nio.file.Files.newByteChannel(Files.java:380)
	at java.base/java.nio.file.Files.newByteChannel(Files.java:432)
	at java.base/java.nio.file.spi.FileSystemProvider.newInputStream(FileSystemProvider.java:422)
	at java.base/java.nio.file.Files.newInputStream(Files.java:160)
	at io.quarkus.bootstrap.runner.QuarkusEntryPoint.doRun(QuarkusEntryPoint.java:52)
	at io.quarkus.bootstrap.runner.QuarkusEntryPoint.main(QuarkusEntryPoint.java:32)
	at Run4.main(Run4.java:12)
```

_Test 5_

```java
///usr/bin/env jbang "$0" "$@" ; exit $?

//REPOS mavencentral,jitpack=https://jitpack.io
//DEPS com.github.jmini:quarkus-picocli-experiment:main-SNAPSHOT
//JAVA 17

import static java.lang.System.*;

public class Run5 {

    public static void main(String... args) throws Throwable {
        io.quarkus.runtime.Quarkus.run(args);
    }
}
```

output:
```
[jbang] Resolving dependencies...
[jbang]    com.github.jmini:quarkus-picocli-experiment:main-SNAPSHOT
[jbang] Dependencies resolved
[jbang] Building jar for Run5.java...
[jbang] Post build with io.quarkus.launcher.JBangIntegration
Sep 27, 2023 6:48:52 AM org.jboss.threads.Version <clinit>
INFO: JBoss Threads version 3.5.0.Final
Sep 27, 2023 6:48:52 AM io.quarkus.deployment.QuarkusAugmentor run
INFO: Quarkus augmentation completed in 1061ms
__  ____  __  _____   ___  __ ____  ______ 
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/ 
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
2023-09-27 06:48:53,576 INFO  [io.quarkus] (main) quarkus 999-SNAPSHOT on JVM (powered by Quarkus 3.4.1) started in 0.459s. 
2023-09-27 06:48:53,590 INFO  [io.quarkus] (main) Profile prod activated. 
2023-09-27 06:48:53,590 INFO  [io.quarkus] (main) Installed features: [cdi, picocli]
```

At the end the `Run5` is the closest to what I would like to get, but obviously the code defined in `quarkus-picocli-experiment-main-9fc985be83-1.jar` is not working.

By comparison, When in my local clone of the [quarkus-picocli-experiment repo](https://github.com/jmini/quarkus-picocli-experiment) I run:

```
./gradle build
java -jar build/quarkus-app/quarkus-run.jar 
```

I get the expected `Hello picocli, go go commando!` in the log:

```
__  ____  __  _____   ___  __ ____  ______ 
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/ 
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
2023-09-27 06:51:05,229 INFO  [io.quarkus] (main) quarkus-picocli-experiment 1.0.0-SNAPSHOT on JVM (powered by Quarkus 3.4.1) started in 0.507s. 
2023-09-27 06:51:05,239 INFO  [io.quarkus] (main) Profile prod activated. 
2023-09-27 06:51:05,239 INFO  [io.quarkus] (main) Installed features: [cdi, picocli]
Hello picocli, go go commando!
2023-09-27 06:51:05,349 INFO  [io.quarkus] (main) quarkus-picocli-experiment stopped in 0.011s
```

Which I would like to get also when running from JBang.

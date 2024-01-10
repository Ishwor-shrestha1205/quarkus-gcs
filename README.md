# quarkus-gcs

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

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
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/quarkus-gcs-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## Related Guides

- Google Cloud Storage ([guide](https://quarkiverse.github.io/quarkiverse-docs/quarkus-google-cloud-services/main/storage.html)): Use Google Cloud Storage object storage service
- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

## Getting notifications in your local terminal from Pub/Sub topic subscription :
Run your Quarkus application, 
```shell script
./gradlew quarkusDev
```
And when you access the http://localhost:8080/subscribe/start endpoint, it will start the Pub/Sub subscriber, which will listen to messages from the specified Pub/Sub topic subscription and process them.

### Related description for docker image and google cloud settings.
https://retailai.atlassian.net/wiki/spaces/~606d5c633e6ea000685b5ab2/pages/2706736514/Running+Quarkus+Application+using+Kotlin+in+Docker+and+GCloud

### Related description for getting log in local environment using pub/sub.
https://retailai.atlassian.net/wiki/spaces/~606d5c633e6ea000685b5ab2/pages/2706736539/Pub+Sub+to+get+log+of+cloud+storage+in+local+machine

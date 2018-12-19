# Swagger-based Java-with-Spring server

Whenever you change the API definition (and also before you run the example
for the first time) you need to run the following script:

```shell
./on-api-update.sh
```

## Running the example

```shell
mvn package
java -jar target/swagger-spring-1.0.0.jar
```

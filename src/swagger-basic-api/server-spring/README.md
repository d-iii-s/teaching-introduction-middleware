# Swagger Based Spring Server

A simple user database generated from a REST API description in Swagger (OpenAPI).

## Running the example

Before building the example, be sure to run the `fetch.sh` script in the parent directory.

```
./build.sh
./run.sh
```

## Compatibility

In addition to the server controller implementation, the following generated files were modified:

- `application.properties` to remove the incorrect `server.contextPath` prefix

- `pom.xml` to add the required `javax.xml.bind` dependency

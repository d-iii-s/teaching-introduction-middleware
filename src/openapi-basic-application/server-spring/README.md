# OpenAPI Based Spring Server

A simple user database generated from a REST API description in OpenAPI.

## Running the example

Before building the example, be sure to run the `fetch.sh` script in the parent directory.

```
./build.sh
./run.sh
```

The user interface is available at https://localhost:8080/v1.

## Compatibility

In addition to the server controller implementation, the following generated files were modified:

- `application.properties` to inject correct base path prefix

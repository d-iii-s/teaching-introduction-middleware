# Swagger Based Flask Server

A simple user database generated from a REST API description in Swagger (OpenAPI).

## Running the example

Before building the example, be sure to run the `fetch.sh` script in the parent directory.

```
./build.sh
./run.sh
```

The user interface is available at https://localhost:8080/v1/ui.

## Compatibility

The generated application does not support CORS requests.
The controller works around this by adding CORS headers manually.
This is not enough for requests with CORS preflight and therefore certain clients will fail.

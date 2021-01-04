# Swagger Based Angular Client

A simple user database client with stubs generated from a REST API description in Swagger (OpenAPI).

## Running the example

Before building the example, be sure to run the `fetch.sh` script in the parent directory.

```
./build.sh
node_modules/@angular/cli/bin/ng serve --open
```

## Compatibility

The following adjustments had to be made:

- add `rxjs-compat` to dependencies because the generated code uses old style `rxjs` import statements

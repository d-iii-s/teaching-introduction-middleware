# Swagger Based API

The example shows a simple user database with a REST API described in Swagger (OpenAPI).
This directory contains the `api-two.yaml` and `api-three.yaml` files with
otherwise equivalent API definitions in OpenAPI 2 and OpenAPI 3.

The individual directories contain bare bone clients and servers implemented
with different technologies supported by Swagger CodeGen. The `fetch.sh` script
fetches the Swagger CodeGen CLI modules used to build the clients and the servers,
and must be run before building inside the individual directories.

## Compatibility

Because Swagger CodeGen is developed independently of the technologies it generates code for,
it is quite sensitive to what versions of these technologies it is used with. Expect issues
especially as this code gets older.

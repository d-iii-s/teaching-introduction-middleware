# OpenAPI Based Application

The example shows a simple user database with a REST API described in OpenAPI.
This directory contains the `api.yaml` file with the OpenAPI 3.0 definition
for the API.

The individual directories contain bare bone clients and servers implemented
with different technologies supported by OpenAPI Generator. The `fetch.sh` script
fetches the OpenAPI Generator CLI module used to build the clients and the servers,
and must be run before building inside the individual directories.

## Compatibility

Because OpenAPI Generator is developed independently of the technologies it generates code for,
it is quite sensitive to what versions of these technologies it is used with. Expect issues
especially as this code gets older.

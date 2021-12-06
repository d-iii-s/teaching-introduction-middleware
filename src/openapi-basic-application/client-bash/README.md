# OpenAPI Based bash Client

A simple user database client with stubs generated from a REST API description in OpenAPI.

## Running the example

Before building the example, be sure to run the `fetch.sh` script in the parent directory.

```
./build.sh
./populate.sh
```

You can also run the client commands directly.

```
target/client.sh readUsers
target/client.sh readUser user_id=1
target/client.sh createUser firstname==Harry lastname==Potter
```

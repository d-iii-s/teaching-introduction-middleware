# gRPC Based Server

## Running the example

```shell
mvn compile
mvn exec:java@server &
mvn exec:java@client
```

## Inspecting server API

Add `grpc-services` to the dependencies in `pom.xml`.
Add `io.grpc.protobuf.services.ProtoReflectionService.newInstance ()` to the server builder invocation.
This enables server reflection, which permits inspecting the API from the clients,
using for example the command line tools.

```shell
grpc_cli ls localhost:8888
grpc_cli ls localhost:8888 --l
grpc_cli type localhost:8888 example.AnExampleMessage
grpc_cli call localhost:8888 example.AnExampleService.CloneMessage "some_integer: 8"
```

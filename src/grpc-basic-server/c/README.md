# gRPC Based Server

## Running the example

```
> make
> ./server &
> ./client
```

## Tracing execution

Launch with `GRPC_VERBOSITY=info` and `GRPC_TRACE=all` to enable logging.

## Inspecting server API

Add `-lgrpc++_reflection` to `LD_OPTS` in `Makefile` and build again.
This enables server reflection, which permits inspecting the API,
using for example command line tools.

```
> grpc_cli ls localhost:8888
> grpc_cli ls localhost:8888 --l
> grpc_cli type localhost:8888 example.AnExampleMessage
> grpc_cli call localhost:8888 example.AnExampleService.CloneMessage "some_integer: 8"
```

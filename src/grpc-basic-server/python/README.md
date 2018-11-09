# gRPC Based Server

## Running the example

```
> python3 -m venv .
> bin/pip install protobuf grpcio
> protoc --plugin=protoc-gen-grpc=${HOME:?}/.local/bin/grpc_python_plugin --python_out="." --grpc_out="." example.proto
> bin/python server.py &
> bin/python client.py
```

# gRPC Based Server

## Running the example

```
> python -m venv .
> . bin/activate
> pip install protobuf grpcio
> protoc --plugin=protoc-gen-grpc=$(pkg-config --variable=prefix grpc++)/bin/grpc_python_plugin --python_out="." --grpc_out="." example.proto
> python server.py &
> python client.py
```

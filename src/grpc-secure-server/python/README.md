# gRPC Based Secure Server

## Running the example

Use `localhost` as CN when generating the certificate.

```
> python3 -m venv .
> bin/pip install protobuf grpcio
> protoc --plugin=protoc-gen-grpc=$(pkg-config --variable=prefix grpc++)/bin/grpc_python_plugin --python_out="." --grpc_out="." example.proto
> openssl req -newkey rsa:4096 -nodes -keyout server.key -x509 -days 365 -out server.crt
> bin/python server.py &
> bin/python client.py
```

# gRPC Based Server

## Running the example

```
python -m venv .venv
. .venv/bin/activate
pip install -r requirements.txt
protoc --plugin=protoc-gen-grpc=$(pkg-config --variable=prefix grpc++)/bin/grpc_python_plugin --python_out="." --grpc_out="." example.proto
./server.py &
./client.py
```

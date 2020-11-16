# gRPC Based Server

## Running the example

```
python -m venv .venv
. .venv/bin/activate
pip install -r requirements.txt
python -m grpc.tools.protoc --python_out="." --grpc_python_out="." --proto_path="." example.proto
./server.py &
./client.py
```

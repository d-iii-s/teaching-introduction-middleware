# Speech Recognition with gRPC Interface

## Getting audio sample

Use for example http://www.voxforge.org. The service can accept WAV and FLAC files.

## Running the example

```
> python3 -m venv .
> bin/pip install requests protobuf grpcio google-auth
> git clone http://github.com/googleapis/googleapis.git
> cd googleapis
> make OUTPUT=.. LANGUAGE=python GRPCPLUGIN=$(pkg-config --variable=prefix grpc++)/bin/grpc_python_plugin
> cd ..
> bin/python client.py /path/to/speech.wav
```

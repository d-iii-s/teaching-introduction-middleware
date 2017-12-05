# Speech Recognition with gRPC Interface

## Getting audio sample

Use for example http://www.voxforge.org. The service can accept WAV and FLAC files.

## Running the example

```
> python -m virtualenv .
> bin/pip install requests grpcio google-auth
> git clone http://github.com/googleapis/googleapis.git
> cd googleapis
> make OUTPUT=.. LANGUAGE=python GRPCPLUGIN=/path/to/grpc_python_plugin
> cd ..
> find google -type d -exec touch {}/__init__.py \;
> bin/python client.py /path/to/speech.wav
```

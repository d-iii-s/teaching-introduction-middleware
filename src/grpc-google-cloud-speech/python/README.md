# Speech Recognition with gRPC Interface

## Getting audio sample

Use for example http://www.voxforge.org. The service can accept WAV and FLAC files.

## Running the example

```
python -m venv .venv
. .venv/bin/activate
pip install -r requirements.txt
git clone http://github.com/googleapis/googleapis.git
cd googleapis
make OUTPUT=.. LANGUAGE=python GRPCPLUGIN=$(pkg-config --variable=prefix grpc++)/bin/grpc_python_plugin
cd ..
./client.py /path/to/speech.wav
```

# Speech Recognition with Client Library

## Getting audio sample

Use for example http://www.voxforge.org. The service can accept WAV and FLAC files.

## Running the example

```
python -m venv .venv
. .venv/bin/activate
pip install -r requirements.txt
export GOOGLE_APPLICATION_CREDENTIALS=/path/to/credentials.json
./client.py /path/to/speech.wav
```

# Speech Recognition with Client Library

## Getting audio sample

Use for example http://www.voxforge.org. The service can accept WAV and FLAC files.

## Running the example

```
> python -m virtualenv .
> bin/pip install google-cloud-speech
> export GOOGLE_APPLICATION_CREDENTIALS=/path/to/credentials.json
> bin/python client.py /path/to/speech.wav
```

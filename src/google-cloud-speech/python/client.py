#!/usr/bin/env python3

import sys

from google.cloud import speech as google_cloud_speech

# Create the object representing the API to the client.
client = google_cloud_speech.SpeechClient ()

content = open (sys.argv [1], 'rb').read ()
audio = google_cloud_speech.RecognitionAudio (content = content)
config = google_cloud_speech.RecognitionConfig (language_code = 'en-US')

# Call the service to perform speech recognition.
result = client.recognize (config = config, audio = audio)

print (result)

import sys

from google.cloud import speech as google_cloud_speech
from google.cloud.speech import enums as google_cloud_speech_enums
from google.cloud.speech import types as google_cloud_speech_types

# Create the object representing the API to the client.
client = google_cloud_speech.SpeechClient ()

content = open (sys.argv [1], 'rb').read ()
audio = google_cloud_speech_types.RecognitionAudio (content = content)
config = google_cloud_speech_types.RecognitionConfig (language_code = 'en-US')

# Call the service to perform speech recognition.
result = client.recognize (config, audio)

print (result)

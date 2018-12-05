import io
import sys
import grpc

from google.cloud.speech.v1.cloud_speech_pb2 import *
from google.cloud.speech.v1.cloud_speech_pb2_grpc import *

from google import auth as google_auth
from google.oauth2 import service_account as google_oauth2_service_account
from google.auth.transport import grpc as google_auth_transport_grpc
from google.auth.transport import requests as google_auth_transport_requests

SCOPES = ['https://www.googleapis.com/auth/cloud-platform']
SERVICE = 'speech.googleapis.com:443'


# We will need credentials to call the service. The default approach
# requires setting the GOOGLE_APPLICATION_CREDENTIALS environment variable.
scoped_credentials, project = google_auth.default (scopes = SCOPES)

# Alternative approach where file is specified directly.
# credentials = google_oauth2_service_account.Credentials.from_service_account_file ('account.json')
# scoped_credentials = credentials.with_scopes (SCOPES)

# The request object represents an HTTP transport layer used to renew tokens.
request = google_auth_transport_requests.Request ()

# Just create a channel, the request object could also be None if token renewal is not needed.
with google_auth_transport_grpc.secure_authorized_channel (scoped_credentials, request, SERVICE) as channel:

    # Create a stub object that provides the service interface.
    stub = SpeechStub (channel)

    # Encoding and sample rate are only needed for RAW files.
    # When using WAV or FLAC files it is detected automatically.
    config = RecognitionConfig ()
    # config.encoding = RecognitionConfig.LINEAR16
    # config.sample_rate_hertz = 16000
    config.language_code = 'en_US'

    audio = RecognitionAudio ()
    audio.content = open (sys.argv [1], 'rb').read ()

    message = RecognizeRequest (config = config, audio = audio)
    print ('Message:')
    print (message)

    # Call the service through the stub object.
    response = stub.Recognize (message)

    print ('Response:')
    print (response)

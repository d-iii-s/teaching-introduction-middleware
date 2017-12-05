import grpc
import time

from concurrent import futures

from shared import *

from example_pb2 import *
from example_pb2_grpc import *


# Service implementation.
#
# The implementation inherits from a generated base class.

class MyServicer (AnExampleServiceServicer):
    def CloneMessage (self, request, context):
        response = MoreExampleMessages ()
        response.messages.add ().CopyFrom (request)
        response.messages.add ().CopyFrom (request)
        return response


# Create the server object.
#
# The server object represents the server runtime.
# It needs to be told what service to provide
# and what port to listen on.

key_data = open ('server.key', 'rb').read ()
crt_data = open ('server.crt', 'rb').read ()
credentials = grpc.ssl_server_credentials ([( key_data, crt_data )])

server = grpc.server (futures.ThreadPoolExecutor (max_workers = SERVER_THREAD_COUNT))
add_AnExampleServiceServicer_to_server (MyServicer (), server)
server.add_secure_port (SERVER_ADDR, credentials)
server.start ()

# Sleep to prevent server termination.

while True:
    time.sleep (1)

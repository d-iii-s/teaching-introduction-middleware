import grpc

from shared import *

from example_pb2 import *
from example_pb2_grpc import *


# Create the channel used to connect to the server.
with grpc.insecure_channel (SERVER_ADDR) as channel:

    # Create a stub object that provides the service interface.
    stub = AnExampleServiceStub (channel)

    message = AnExampleMessage ()
    message.some_integer = 0xDEAD
    message.some_string = 'Hello gRPC !'
    print ('Message:')
    print (message)

    # Call the service through the stub object.
    response = stub.CloneMessage (message)

    print ('Response:')
    print (response)

import socket

from shared import *

with socket.socket (socket.AF_INET, socket.SOCK_STREAM) as server_socket:
    server_socket.bind (('', SERVER_PORT))
    server_socket.listen (SERVER_SOCKET_BACKLOG)

    print ('Waiting for incoming connection.')

    while True:
        with server_socket.accept () [0] as client_socket:

            print ('Accepted an incoming connection.')

            while True:
                data = client_socket.recv (SOCKET_BUFFER_SIZE)
                if len (data) == 0:
                    break
                client_socket.send (data)

            print ('Client disconnected.')

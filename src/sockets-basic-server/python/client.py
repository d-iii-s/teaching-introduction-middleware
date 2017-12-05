import socket

from shared import *

with socket.socket (socket.AF_INET, socket.SOCK_STREAM) as client_socket:
    client_socket.connect ((SERVER_ADDR, SERVER_PORT))
    client_socket.send ('Hello !'.encode ('utf-8'))
    print ('Received message:', client_socket.recv (SOCKET_BUFFER_SIZE).decode ('utf-8'))

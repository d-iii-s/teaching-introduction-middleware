#include "shared.h"

int main ()
{
    // Create client socket object.
    //
    // The object state resides in the operating system kernel.
    // What we have is merely a file descriptor refering to it.

    int client_socket = socket (AF_INET, SOCK_STREAM, 0);
    ASSERT (client_socket != -1, "Failed to create client socket object.");

    // Construct server address.
    //
    // Note the need to use network order inside address fields.

    struct sockaddr_in server_address;
    server_address.sin_family = AF_INET;
    int aton_status = inet_aton (SERVER_ADDR, (in_addr *) &server_address.sin_addr.s_addr);
    ASSERT (aton_status == 1, "Failed to parse server address.");
    server_address.sin_port = htons (SERVER_PORT);

    // Connect to server.

    int connect_status = connect (client_socket, (struct sockaddr *) &server_address, sizeof (server_address));
    ASSERT (connect_status == 0, "Failed to connect to server.");
    printf ("Established outgoing connection.\n");

    // Just send and receive something.
    //
    // We assume that the response is a zero terminated string.

    const char *message = "Hello !";
    ssize_t message_size = strlen (message);
    ssize_t write_size = write (client_socket, message, message_size);
    ASSERT (write_size == message_size, "Failed to write to outgoing connection.");

    char buffer [SOCKET_BUFFER_SIZE];
    ssize_t read_size = read (client_socket, buffer, sizeof (buffer));
    ASSERT (read_size >= 0, "Failed to read from outgoing connection.");
    printf ("Received message: %s\n", buffer);

    // Clean up by closing the socket.
    //
    // Shutdown precedes close to make sure protocol level shutdown is executed completely.
    // Close without shutdown may use RST instead of FIN to terminate connection, dropping data that is in flight.
    //
    // It is also possible to use shutdown to close input and output streams independently.

    int shutdown_status = shutdown (client_socket, SHUT_RDWR);
    ASSERT (shutdown_status == 0, "Failed to shutdown incoming connection.");

    int close_status = close (client_socket);
    ASSERT (close_status == 0, "Failed to close incoming connection.");
}

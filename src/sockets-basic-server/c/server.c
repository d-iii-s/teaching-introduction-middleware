#include "shared.h"

int main ()
{
    // Create server socket object.
    //
    // The object state resides in the operating system kernel.
    // What we have is merely a file descriptor refering to it.

    int server_socket = socket (AF_INET, SOCK_STREAM, 0);
    ASSERT (server_socket != -1, "Failed to create server socket object.");

    // Bind socket to listen to constant port on all local interfaces.
    //
    // Note the need to use network order inside address fields.

    struct sockaddr_in server_address;
    server_address.sin_family = AF_INET;
    server_address.sin_addr.s_addr = INADDR_ANY;
    server_address.sin_port = htons (SERVER_PORT);

    int bind_status = bind (server_socket, (struct sockaddr *) &server_address, sizeof (server_address));
    ASSERT (bind_status == 0, "Failed to bind to server socket.");

    // Start listening for connections on server socket.
    //
    // The function merely indicates to the operating system kernel
    // that the socket will accept incoming connection requests.
    // No waiting happens inside this function.

    int listen_status = listen (server_socket, SERVER_SOCKET_BACKLOG);
    ASSERT (listen_status == 0, "Failed to listen on server socket.");

    // The rest happens in an infinite loop.

    while (TRUE) {

        // Accept an incoming connection.

        printf ("Waiting for incoming connection.\n");

        struct sockaddr_in client_address;
        socklen_t client_address_size = sizeof (client_address);
        int client_socket = accept (server_socket, (struct sockaddr *) &client_address, &client_address_size);
        ASSERT (client_socket >= 0, "Failed to accept incoming connection.");

        printf ("Accepted an incoming connection.\n");

        // Just copy everything back to the client.

        char buffer [SOCKET_BUFFER_SIZE];
        while (TRUE) {
            ssize_t read_size = read (client_socket, buffer, sizeof (buffer));
            ASSERT (read_size >= 0, "Failed to read from incoming connection.");
            if (read_size == 0) break;
            ssize_t write_size = write (client_socket, buffer, read_size);
            ASSERT (write_size == read_size, "Failed to write to incoming connection.");
        }

        printf ("Client disconnected.\n");

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

    // Should not be reached.

    return (0);
}

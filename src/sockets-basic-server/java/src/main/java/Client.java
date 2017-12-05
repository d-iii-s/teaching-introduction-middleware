import java.io.InputStream;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main (String [] args) {

        // Create a socket object and connect it to the server.
        try (Socket server_socket = new Socket (Shared.SERVER_ADDR, Shared.SERVER_PORT)) {

            System.out.println ("Established outgoing connection.");

            try (
                // Wrap the socket streams in appropriate readers and writers.
                InputStream input = server_socket.getInputStream ();
                OutputStream output = server_socket.getOutputStream ();
                InputStreamReader reader = new InputStreamReader (input);
                BufferedReader buffered = new BufferedReader (reader);
                PrintWriter writer = new PrintWriter (output, true);
            ) {
                // Just send and receive something.
                writer.println ("Hello !");
                System.out.println ("Received message: " + buffered.readLine ());
            }
        }
        catch (Exception e) {
            System.out.println (e);
        }
    }
}

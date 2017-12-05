import example.Example.AnExampleMessage;
import example.Example.MoreExampleMessages;

import example.AnExampleServiceGrpc;


public class Client {

    public static void main (String [] args) {

        // Create the channel used to connect to the server.
        io.grpc.ManagedChannel channel = io.grpc.ManagedChannelBuilder
            .forAddress (Shared.SERVER_ADDR, Shared.SERVER_PORT)
            .usePlaintext (true)
            .build ();

        // Create a stub object that provides the service interface.
        AnExampleServiceGrpc.AnExampleServiceBlockingStub stub = AnExampleServiceGrpc.newBlockingStub (channel);

        AnExampleMessage message = AnExampleMessage.newBuilder ()
            .setSomeInteger (0xDEAD)
            .setSomeString ("Hello gRPC !")
            .build ();
        System.out.println ("Message:");
        System.out.println (message.toString ());

        try {
            // Call the service through the stub object.
            MoreExampleMessages response = stub.cloneMessage (message);

            System.out.println ("Response:");
            System.out.println (response.toString ());
        }
        catch (Exception e) {
            System.out.println (e);
        }
    }
}

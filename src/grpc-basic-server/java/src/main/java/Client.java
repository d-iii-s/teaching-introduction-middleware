import java.util.concurrent.TimeUnit;

import example.Example.AnExampleMessage;
import example.Example.MoreExampleMessages;

import example.AnExampleServiceGrpc;


public class Client {

    public static void main (String [] args) throws Exception {

        // Create the channel used to connect to the server.
        // Sadly channels do not support try with resources.
        io.grpc.ManagedChannel channel = io.grpc.ManagedChannelBuilder
            .forAddress (Shared.SERVER_ADDR, Shared.SERVER_PORT)
            .usePlaintext ()
            .build ();

        try {
            // Create a stub object that provides the service interface.
            AnExampleServiceGrpc.AnExampleServiceBlockingStub stub = AnExampleServiceGrpc.newBlockingStub (channel);

            AnExampleMessage message = AnExampleMessage.newBuilder ()
                .setSomeInteger (0xDEAD)
                .setSomeString ("Hello gRPC !")
                .build ();
            System.out.println ("Message:");
            System.out.println (message.toString ());

            // Call the service through the stub object.
            MoreExampleMessages response = stub.cloneMessage (message);

            System.out.println ("Response:");
            System.out.println (response.toString ());
        }
        finally {
            // Make sure to close the channel and release resources.
            channel.shutdown ().awaitTermination (8, TimeUnit.SECONDS);
        }
    }
}

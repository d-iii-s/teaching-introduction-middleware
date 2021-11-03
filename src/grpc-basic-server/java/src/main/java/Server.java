import example.Example.AnExampleMessage;
import example.Example.MoreExampleMessages;

import example.AnExampleServiceGrpc;


public class Server {

    // Service implementation.
    //
    // The implementation inherits from a generated base class.

    static class MyService extends AnExampleServiceGrpc.AnExampleServiceImplBase {

        @Override
        public void cloneMessage (AnExampleMessage request, io.grpc.stub.StreamObserver<MoreExampleMessages> responseObserver) {

            // Print the input.

            System.out.println ("gRPC server in Java cloning:");
            System.out.println (request.toString ());

            // Create the response by copying the request twice.

            MoreExampleMessages response = MoreExampleMessages.newBuilder ()
                .addMessages (request)
                .addMessages (request)
                .build ();

            // Responses are returned through observer interface.
            // The same interface is used for stream arguments.

            responseObserver.onNext (response);
            responseObserver.onCompleted ();
        }
    }

    public static void main (String [] args) {

        // Create the server object.
        //
        // The server object represents the server runtime.
        // It needs to be told what service to provide
        // and what port to listen on.

        try {
            io.grpc.Server server = io.grpc.ServerBuilder
                .forPort (Shared.SERVER_PORT)
                .addService (new MyService ())
                .build ()
                .start ();

            // The server is never asked to terminate in this example,
            // it therefore waits here until interrupted from outside.

            server.awaitTermination ();
        }
        catch (Exception e) {
            System.out.println (e);
        }
    }
}

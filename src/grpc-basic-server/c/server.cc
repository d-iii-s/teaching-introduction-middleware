#include <grpc++/grpc++.h>
// To make the API calls easily visible, the example does not use the grpc namespace.
// A standard application would likely be "using namespace grpc" here.

#include "shared.h"

#include "example.grpc.pb.h"
using namespace example;


// Service implementation.
//
// The implementation inherits from a generated base class.

class MyService : public AnExampleService::Service {
    grpc::Status CloneMessage (grpc::ServerContext *context, const AnExampleMessage *request, MoreExampleMessages *response) override {
        response->add_messages ()->CopyFrom (*request);
        response->add_messages ()->CopyFrom (*request);
        return (grpc::Status::OK);
    }
};


int main ()
{
    // Create the server object.
    //
    // The server object represents the server runtime.
    // It needs to be told what service to provide
    // and what port to listen on.

    MyService service;
    grpc::ServerBuilder builder;
    builder.AddListeningPort (SERVER_ADDR, grpc::InsecureServerCredentials ());
    builder.RegisterService (&service);
    std::unique_ptr<grpc::Server> server (builder.BuildAndStart ());

    // The server is never asked to terminate in this example,
    // it therefore waits here until interrupted from outside.

    server->Wait ();

    return (0);
}

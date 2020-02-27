#include <iostream>

#include <grpc++/grpc++.h>
// To make the API calls easily visible, the example does not use the grpc namespace.
// A standard application would likely be "using namespace grpc" here.

#include "shared.h"

#include "example.grpc.pb.h"
using namespace example;


int main ()
{
    // Create the channel used to connect to the server.
    std::shared_ptr<grpc::Channel> channel = grpc::CreateChannel (SERVER_ADDR, grpc::InsecureChannelCredentials ());

    // Create a stub object that provides the service interface.
    std::shared_ptr<AnExampleService::Stub> stub = AnExampleService::NewStub (channel);

    AnExampleMessage message;
    message.set_some_integer (0xDEAD);
    message.set_some_string ("Hello gRPC !");
    std::cout << "Message:" << std::endl;
    std::cout << message.DebugString () << std::endl;

    grpc::ClientContext context;
    MoreExampleMessages response;

    // Call the service through the stub object.
    grpc::Status status = stub->CloneMessage (&context, message, &response);

    if (status.ok ()) {
        std::cout << "Response:" << std::endl;
        std::cout << response.DebugString () << std::endl;
    }

    return (0);
}

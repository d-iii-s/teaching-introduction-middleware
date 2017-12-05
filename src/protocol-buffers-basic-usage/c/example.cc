#include <string>
#include <iomanip>
#include <iostream>

#include "example.pb.h"
using namespace example;

#define BUFFER_SIZE 16384
#define DUMP_LINE_SIZE 16


// Helper function to dump buffer content.
void dump_buffer (unsigned char *buffer, size_t size) {

    // Preserve output stream settings across invocation.
    std::ios stream_state (NULL);
    stream_state.copyfmt (std::cout);

    // Switch output to hex base.
    std::cout << std::hex << std::setfill ('0');

    for (size_t offset = 0 ; offset < size ; offset += DUMP_LINE_SIZE) {

        // Offset column.
        std::cout << std::setw (8) << offset;

        // Show numeric values.
        for (size_t position = 0 ; position < DUMP_LINE_SIZE ; position ++) {
            std::cout << " ";
            if (offset + position < size) std::cout << std::setw (2) << (int) buffer [offset + position];
                                     else std::cout << "--";
        }

        // Show character values.
        std::cout << " ";
        for (size_t position = 0 ; position < DUMP_LINE_SIZE ; position ++) {
            if (offset + position < size) {
                if (buffer [offset + position] < ' ') std::cout << '.';
                                                 else std::cout << buffer [offset + position];
            } else std::cout << '.';
        }

        // New line;
        std::cout << std::endl;
    }

    // Preserve output stream settings across invocation.
    std::cout.copyfmt (stream_state);
}


// Helper function to dump message content.
void dump_message (const google::protobuf::Message &message) {
    unsigned char buffer [BUFFER_SIZE];
    message.SerializeToArray (buffer, sizeof (buffer));
    size_t size = message.ByteSizeLong ();
    std::cout << "Buffer size is " << size << " bytes." << std::endl;
    dump_buffer (buffer, size);
}


int main (void) {

    // Library version check.
    // Aborts on version mismatch.
    GOOGLE_PROTOBUF_VERIFY_VERSION;

    // Show what a serialized empty message looks like.
    std::cout << "Empty message:" << std::endl;
    dump_message (AnExampleMessage::default_instance ());
    std::cout << std::endl;

    // Fill two out of three fields and show what the serialized message looks like.
    AnExampleMessage message_one;
    message_one.set_some_integer (0xDEAD);
    message_one.set_some_string ("Hello Protocol Buffers !");
    std::cout << "Message with one integer field and one string field:" << std::endl;
    dump_message (message_one);
    std::cout << std::endl;

    // Fill the third field and show what the serialized message looks like.
    AnExampleMessage message_two (message_one);
    message_two.set_another_integer (0xBEEF);
    std::cout << "Message with two integer fields and one string field:" << std::endl;
    dump_message (message_two);
    std::cout << std::endl;

    // Create message instance with repeated fields.
    MoreExampleMessages messages;
    messages.add_messages ()->CopyFrom (message_one);
    messages.add_messages ()->CopyFrom (message_two);
    std::cout << "Message with two more embedded messages:" << std::endl;
    dump_message (messages);
    std::cout << std::endl;

    // Encode and decode message instance to show it also works.
    char buffer [BUFFER_SIZE];
    messages.SerializeToArray (buffer, sizeof (buffer));
    MoreExampleMessages parsed_messages;
    parsed_messages.ParseFromArray (buffer, sizeof (buffer));
    std::cout << "Encoded and decoded message:" << std::endl;
    for (int i = 0 ; i < parsed_messages.messages_size () ; i ++) {
        const AnExampleMessage &parsed_message = parsed_messages.messages (i);
        std::cout << parsed_message.some_integer () <<
            " - " << parsed_message.another_integer () <<
            " - " << parsed_message.some_string () << std::endl;
    }
    std::cout << std::endl;

    // We can also debug dump a message.
    std::string dump = messages.DebugString ();
    std::cout << "Debug dump:" << std::endl;
    std::cout << dump << std::endl;
}

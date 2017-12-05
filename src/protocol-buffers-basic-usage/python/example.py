from example_pb2 import *


DUMP_LINE_SIZE = 16


def dumpBuffer (buffer):
    for offset in range (0, len (buffer), DUMP_LINE_SIZE):
        print ('{offset:08x}'.format (offset = offset), end = '')
        for position in range (0, DUMP_LINE_SIZE):
            if offset + position < len (buffer):
                print (' {data:02x}'.format (data = buffer [offset + position]), end = '')
            else:
                print (' --', end = '')
        print (' ', end = '')
        for position in range (0, DUMP_LINE_SIZE):
            if offset + position < len (buffer):
                if buffer [offset + position] < ord (' '):
                    print ('.', end = '')
                else:
                    print ('{data:1c}'.format (data = buffer [offset + position]), end = '')
            else:
                print ('.', end = '')
        print ()


def dumpMessage (message):
    print ('Buffer size is', message.ByteSize (), 'bytes.')
    dumpBuffer (message.SerializeToString ())


# Show what a serialized empty message looks like.
print ('Empty message:')
dumpMessage (AnExampleMessage ())
print ()

# Fill two out of three fields and show what the serialized message looks like.
message_one = AnExampleMessage ()
message_one.some_integer = 0xDEAD
message_one.some_string = 'Hello Protocol Buffers !'
print ('Message with one integer field and one string field:')
dumpMessage (message_one)
print ()

# Fill the third field and show what the serialized message looks like.
message_two = AnExampleMessage ()
message_two.CopyFrom (message_one)
message_two.another_integer = 0xBEEF
print ('Message with two integer fields and one string field:')
dumpMessage (message_two)
print ()

# Create message instance with repeated fields.
messages = MoreExampleMessages ()
messages.messages.add ().CopyFrom (message_one)
messages.messages.add ().CopyFrom (message_two)
print ('Message with two more embedded messages:')
dumpMessage (messages)
print ()

# Encode and decode message instance to show it also works.
buffer = messages.SerializeToString ()
parsed_messages = MoreExampleMessages.FromString (buffer)
print ('Encoded and decoded message:')
for message in messages.messages:
    print (message.some_integer, '-', message.another_integer, '-', message.some_string)
print ()

# We can also debug dump a message.
print ('Debug dump:')
print (messages)

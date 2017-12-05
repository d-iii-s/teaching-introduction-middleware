import com.google.protobuf.Message;
import com.google.protobuf.InvalidProtocolBufferException;

import example.Example.AnExampleMessage;
import example.Example.MoreExampleMessages;

public class Example {

    public static final int DUMP_LINE_SIZE = 16;

    public static void dumpBuffer (byte [] buffer) {
        for (int offset = 0 ; offset < buffer.length ; offset += DUMP_LINE_SIZE) {

            // This is where we collect the output.
            StringBuilder builder = new StringBuilder ();

            // Offset column.
            builder.append (String.format ("%08x", offset));

            // Show numeric values.
            for (int position = 0 ; position < DUMP_LINE_SIZE ; position ++) {
                builder.append (" ");
                if (offset + position < buffer.length) builder.append (String.format ("%02x", buffer [offset + position]));
                                                  else builder.append ("--");
            }

            // Show character values.
            builder.append (" ");
            for (int position = 0 ; position < DUMP_LINE_SIZE ; position ++) {
                if (offset + position < buffer.length) {
                    if (buffer [offset + position] < ' ') builder.append (".");
                                                     else builder.append ((char) buffer [offset + position]);
                }
            }

            // New line.
            System.out.println (builder);
        }
    }


    public static void dumpMessage (Message message) {
        byte [] buffer = message.toByteArray ();
        System.out.println ("Buffer size is " + buffer.length + " bytes.");
        dumpBuffer (buffer);
    }


    public static void main (String [] args) {

        // Show what a serialized empty message looks like.
        System.out.println ("Empty message:");
        dumpMessage (AnExampleMessage.getDefaultInstance ());
        System.out.println ();

        // Fill two out of three fields and show what the serialized message looks like.
        AnExampleMessage message_one = AnExampleMessage.newBuilder ()
            .setSomeInteger (0xDEAD)
            .setSomeString ("Hello Protocol Buffers !")
            .build ();
        System.out.println ("Message with one integer field and one string field:");
        dumpMessage (message_one);
        System.out.println ();

        // Fill the third field and show what the serialized message looks like.
        AnExampleMessage message_two = AnExampleMessage.newBuilder (message_one)
            .setAnotherInteger (0xBEEF)
            .build ();
        System.out.println ("Message with two integer fields and one string field:");
        dumpMessage (message_two);
        System.out.println ();

        // Create message instance with repeated fields.
        MoreExampleMessages messages = MoreExampleMessages.newBuilder ()
            .addMessages (message_one)
            .addMessages (message_two)
            .build ();
        System.out.println ("Message with two more embedded messages:");
        dumpMessage (messages);
        System.out.println ();

        // Encode and decode message instance to show it also works.
        byte [] buffer = messages.toByteArray ();
        try {
            MoreExampleMessages parsed_messages = MoreExampleMessages.parseFrom (buffer);
        } catch (InvalidProtocolBufferException e) {
            System.out.println (e);
        }
        System.out.println ("Encoded and decoded message:");
        for (AnExampleMessage message : messages.getMessagesList ()) {
            System.out.println (message.getSomeInteger () + " - " + message.getAnotherInteger () + " - " + message.getSomeString ());
        }
        System.out.println ();

        // We can also debug dump a message.
        System.out.println ("Debug dump:");
        System.out.println (messages.toString ());
    }
}

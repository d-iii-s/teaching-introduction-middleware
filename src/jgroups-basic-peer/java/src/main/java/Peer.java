import java.util.Scanner;

import org.jgroups.JChannel;
import org.jgroups.Message;

public class Peer {

    public static void main (String [] arguments) {
        try {
            JChannel channel = new JChannel ();
            channel.connect ("ExampleCluster");
            channel.setReceiver (new ReceiverEventHandler ());

            Scanner scanner = new Scanner (System.in);
            while (true) {
                String line = scanner.nextLine ();
                Message message = new Message (null, line);
                channel.send (message);
            }
        }
        catch (Exception e) {
            System.out.println (e);
        }
    }
}

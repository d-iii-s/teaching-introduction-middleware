import java.util.Scanner;

import org.jgroups.JChannel;
import org.jgroups.ObjectMessage;

public class Peer {

    public static void main (String [] arguments) {
        try {
            JChannel channel = new JChannel ();
            channel.connect ("ExampleCluster");
            channel.setReceiver (new ReceiverEventHandler ());

            Scanner scanner = new Scanner (System.in);
            while (true) {
                String line = scanner.nextLine ();
                ObjectMessage message = new ObjectMessage (null, line);
                channel.send (message);
            }
        }
        catch (Exception e) {
            System.out.println (e);
        }
    }
}

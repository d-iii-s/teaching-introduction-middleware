import org.jgroups.Receiver;
import org.jgroups.Message;
import org.jgroups.View;

public class ReceiverEventHandler implements Receiver {

    public void viewAccepted (View view) {
        System.out.println ("New view contains " + view.size () + " members.");
        System.out.println ("- " + view);
    }

    public void receive (Message message) {
        System.out.println ("Message received.");
        System.out.println ("- " + message);
        System.out.println ("- " + message.getObject ());
    }
}

import java.util.ArrayList;

public class ClassicalChannel
{
    ArrayList<ClassicalChannelRecipient> recipients;

    public ClassicalChannel() {
        this.recipients = new ArrayList<>();
    }

    void sendMessage(ClassicalChannelRecipient sender, Message m){
        for (ClassicalChannelRecipient r : recipients){
            if(!r.equals(sender)){
                // Ignore ourselves to prevent message from echoing.
                r.receiveClassical(m);
	    }
	}
    }

    void addRecipient(ClassicalChannelRecipient rec){
        recipients.add(rec);
    }


}

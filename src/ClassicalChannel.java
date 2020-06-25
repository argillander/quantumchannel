import java.util.ArrayList;

public class ClassicalChannel extends Channel
{



    public ClassicalChannel(ChannelRecipient r, int inport, int targetport) {
        super(r, inport, targetport, Message.class);
    }


    public void sendMessage(Message m){
        super.send(m);
    }



}

import java.util.ArrayList;

public interface Channel
{
    ArrayList<ChannelRecipient> recipients = null;
    void send(Qubit qb);

    void addRecipient(ChannelRecipient p);

}

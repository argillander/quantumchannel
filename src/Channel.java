import java.util.ArrayList;

public interface Channel
{
    ArrayList<ChannelRecipient> recipients = null;
    PolarizationQubit send(PolarizationQubit qb);

    void addRecipient(ChannelRecipient p);

}

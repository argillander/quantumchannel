import java.util.ArrayList;

public interface QuantumChannel
{
    ArrayList<ChannelRecipient> recipients = null;
    PolarizationQubit send(PolarizationQubit qb);

    void addRecipient(ChannelRecipient p);

}

import java.util.ArrayList;

public interface QuantumChannel
{
    ArrayList<QuantumChannelRecipient> recipients = null;
    PolarizationQubit send(QuantumChannelRecipient sender, PolarizationQubit qb);

    void addRecipient(QuantumChannelRecipient p);

}

import java.util.ArrayList;

public class LosslessQuantumChannel implements Channel
{
    ArrayList<ChannelRecipient> recipients = null;

    public LosslessQuantumChannel() {
        this.recipients = new ArrayList<>();
    }

    @Override public PolarizationQubit send(final PolarizationQubit qb) {
        for (ChannelRecipient cr : this.recipients){
            cr.receiveQubit(qb);
	}
        return qb;
    }

    @Override public void addRecipient(final ChannelRecipient p) {
	this.recipients.add(p);
    }
}

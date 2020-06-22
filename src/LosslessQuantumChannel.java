import java.util.ArrayList;

public class LosslessQuantumChannel implements QuantumChannel
{
    ArrayList<QuantumChannelRecipient> recipients = null;

    public LosslessQuantumChannel() {
        this.recipients = new ArrayList<>();
    }

    @Override public PolarizationQubit send(final QuantumChannelRecipient sender, final PolarizationQubit qb) {
        for (QuantumChannelRecipient cr : this.recipients){
            if(!cr.equals(sender)) {
                //Prevent echoing of qubits
                cr.receiveQubit(qb);
            }
	}
        return qb;
    }

    @Override public void addRecipient(final QuantumChannelRecipient p) {
	this.recipients.add(p);
    }
}

import java.net.Socket;
import java.util.ArrayList;

public class LosslessQuantumChannel extends QuantumChannel
{
    ArrayList<QuantumChannelRecipient> recipients = null;

    public LosslessQuantumChannel() {
        this.recipients = new ArrayList<>();
        Socket sock = new Socket();

    }

     public PolarizationQubit send(final QuantumChannelRecipient sender, final PolarizationQubit qb) {
        for (QuantumChannelRecipient cr : this.recipients){
            if(!cr.equals(sender)) {
                //Prevent echoing of qubits
                cr.receiveQubit(qb);
            }
	}
        return qb;
    }

     public void addRecipient(final QuantumChannelRecipient p) {
	this.recipients.add(p);
    }

}

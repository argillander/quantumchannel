public class Alice implements QuantumChannelRecipient,  ClassicalChannelRecipient
{
    QuantumChannel quantumChannel;
    ClassicalChannel classicalChannel;
    int[] rawkey;
    int[] basisSelection;
    int n_bits;

    public Alice(final int n_bits, final QuantumChannel quantumChannel, ClassicalChannel classicalChannel) {
	this.quantumChannel = quantumChannel;
	this.classicalChannel = classicalChannel;

    }

    PolarizationQubit sendOneQubit(){
        PolarizationQubit qb = new PolarizationQubit(Polarization.V);
        System.out.println("Alice [Q]: Sent " + qb.getPolarization());

        this.sendQubit(qb);

        return qb;
    }
    @Override public void receiveClassical(final Message m) {
        System.out.println("Alice [C]: " + m);
    }
    @Override public void sendClassical(final Message m) {
        this.classicalChannel.sendMessage(this,m);
    }

    @Override public PolarizationQubit receiveQubit(final PolarizationQubit qb) {
        System.out.println("Alice [Q]: " + qb);
        return qb;
    }

    @Override public PolarizationQubit sendQubit(final PolarizationQubit qb) {
        this.quantumChannel.send(this, qb);
        return qb;
    }
}

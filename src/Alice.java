import java.util.Arrays;
import java.util.Random;

public class Alice implements QuantumChannelRecipient,  ClassicalChannelRecipient
{
    Random rand;
    QuantumChannel quantumChannel;
    ClassicalChannel classicalChannel;
    int[] rawBits;
    FilterSetting[] basisSelection;
    int nBits;

    public Alice(final int nBits, final QuantumChannel quantumChannel, ClassicalChannel classicalChannel) {
	this.quantumChannel = quantumChannel;
	this.classicalChannel = classicalChannel;
        this.rand = new Random();
        this.nBits = nBits;

        this.basisSelection = new FilterSetting[nBits];
        this.rawBits = new int[nBits];
        randomizeBasisSelection();
        randomizeRawBits();
    }

    private PolarizationQubit sendOneQubit(){
        PolarizationQubit qb = new PolarizationQubit(Polarization.V);
        System.out.println("Alice [Q]: Sent " + qb.getPolarization());

        this.sendQubit(qb);
        return qb;
    }

    private void randomizeRawBits(){
        int b;
        for (int i = 0; i < nBits; i++) {
            b = rand.nextInt(1+1);
            rawBits[i] = b;
        }
        System.out.println("Alice's raw bits");
        System.out.println(Arrays.toString(rawBits));
    }

    private void randomizeBasisSelection(){
        System.out.println("Randomizing Alice's basis. n=" + nBits);
        FilterSetting fs;
        for (int i = 0; i < nBits; i++) {
            fs = PolarizationUtil.getRandomBasis();
            this.basisSelection[i] = fs;
            System.out.println(fs);
        }
        System.out.println("Alice's basis");
        System.out.println(Arrays.toString(this.basisSelection));
    }


    public void doQubitExchange(){
        for (int i = 0; i < nBits; i++) {
            PolarizationQubit qb = new PolarizationQubit();

        }
    }


    public void sendQubits(){

        for (int i = 0; i < nBits; i++) {

        }
    }



    void siftQubits(){

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

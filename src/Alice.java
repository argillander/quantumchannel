import javax.sound.sampled.AudioFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Alice implements QuantumChannelRecipient,  ClassicalChannelRecipient
{
    Random rand;
    QuantumChannel quantumChannel;
    ClassicalChannel classicalChannel;
    ArrayList<Integer> rawBits;
    FilterSetting[] basisSelection;
    int nBits;
    int basisPointer;

    public Alice(final int nBits, final QuantumChannel quantumChannel, ClassicalChannel classicalChannel) {
	this.quantumChannel = quantumChannel;
	this.classicalChannel = classicalChannel;
        this.rand = new Random();
        this.nBits = nBits;
        this.basisPointer = 0;
        this.basisSelection = new FilterSetting[nBits];
        this.rawBits = new ArrayList<Integer>();
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
            rawBits.add(b);
        }
        System.out.println("Alice's raw bits " +rawBits);
    }

    private void randomizeBasisSelection(){
        System.out.println("Randomizing Alice's basis. n=" + nBits);
        this.basisSelection = PolarizationUtil.getRandomBasisSelection(nBits);
        System.out.println("Alice's basis");
        System.out.println(Arrays.toString(this.basisSelection));
    }


    public void doQubitExchange(){
        for (int i = 0; i < nBits; i++) {
            PolarizationQubit qb;
            int dataBit = rawBits.get(i);
            FilterSetting basis = basisSelection[i];
            qb = EncodingScheme.encodeQubit(dataBit, basis);
            System.out.println("Alice [Q]: Sent " + qb.getPolarization());

            sendQubit(qb);
        }
    }


    public void communicateBasisChoices(){
        for (int i = 0; i < nBits ; i++) {
            classicalChannel.sendMessage(this, new Message(ClassicalMessageType.SIFT_REVEAL_BASIS_CHOICE,i, basisSelection[i].toString()));
        }
    }

    public void finalizeSift(){
        classicalChannel.sendMessage(this, new Message(ClassicalMessageType.SIFT_DONE,0, null));

    }
    public void printBasis(){
        System.out.println("Ali's basis: " + Arrays.toString(basisSelection));
    }

    public void printFinalKey(){
        System.out.println("Alices's final key:");
        System.out.println(rawBits);
        System.out.println();
    }

    @Override public void receiveClassical(final Message m) {
        int bitIndex = m.getBitIndex();
        switch(m.getMessageType()){
            case SIFT_DECIDE_KEEP:
                //basisPointer++;
                break;
            case SIFT_DECIDE_REMOVE:
                rawBits.set(bitIndex, -2);
                //basisPointer++;
                break;
            case SIFT_DONE:
                rawBits.removeAll(Collections.singleton(-2));
                break;

        }

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
//        System.out.println("Alice [Q]: Sent " + qb.getPolarization());
        this.quantumChannel.send(this, qb);
        return qb;
    }


}

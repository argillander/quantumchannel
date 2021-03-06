import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Bob implements QuantumChannelRecipient, ClassicalChannelRecipient
{

    FilterSetting[] basisSelection; // = {FilterSetting.HV, FilterSetting.PM, FilterSetting.PM, FilterSetting.HV, FilterSetting.PM };
    int basisPointer = 0;
    Random rand;
    int nBits = 0;
    ClassicalChannel classicalChannel;
    QuantumChannel quantumChannel;

    ArrayList<Integer> rawKey;

    public Bob(final int nBits, final QuantumChannel quantumChannel) {
	this.nBits = nBits;
	this.quantumChannel = quantumChannel;
	this.rand = new Random();
	this.basisSelection = PolarizationUtil.getRandomBasisSelection(nBits);
	this.rawKey = new ArrayList<>();
    }

    public void setClassicalChannel(final ClassicalChannel classicalChannel) {
	this.classicalChannel = classicalChannel;
    }

    public void setQuantumChannel(final QuantumChannel quantumChannel) {
	this.quantumChannel = quantumChannel;
    }

    public PolarizationQubit receiveQubit(final PolarizationQubit qb) {
        PolarizationQubit recv;
        PolarizationFilter pf = new PolarizationFilter(basisSelection[basisPointer]);
	recv = pf.filterQubit(qb);
	this.rawKey.add(EncodingScheme.decodeQubit(recv));

	System.out.println("Bob   [Q]: Recv " + recv.getPolarization());
	System.out.println();


	basisPointer++;
	if(basisPointer == nBits) {
	    System.out.println("Bob's received bits: " + this.rawKey.toString());
	    basisPointer = 0;
	}


	return qb;
    }



    public void printFinalKey(){
	System.out.println("Bob's final key:");
	System.out.println(rawKey);
	System.out.println();
    }
    public void printBasis(){
	System.out.println("Bob's basis: " + Arrays.toString(basisSelection));
    }


    public PolarizationQubit sendQubit(final PolarizationQubit qb) {
	return null;
    }

    public void receiveClassical(final Message m) {
	int bitIndex = m.getBitIndex();

	switch(m.getMessageType()){
	    case SIFT_REVEAL_BASIS_CHOICE:
	        FilterSetting fs = FilterSetting.valueOf(m.getMessage());
	        if (basisSelection[bitIndex] != fs){

	            assert(rawKey.size() == nBits);

		    // Discard the bit. Basis choices do not agree
		    try {
			rawKey.set(bitIndex, -2);
		    }catch(Exception e){
			System.out.println(rawKey);
			System.out.println(fs);
			System.out.println(bitIndex);
			return;
		    }
	            //basisPointer++;
		    classicalChannel.sendMessage(new Message(ClassicalMessageType.SIFT_DECIDE_REMOVE,bitIndex, null));
		}else{
	            // Keep the bit. Basis choices agree
		    //basisPointer++;
		    classicalChannel.sendMessage(new Message(ClassicalMessageType.SIFT_DECIDE_KEEP, bitIndex,null));
		}
	    case SIFT_DONE:
	        rawKey.removeAll(Collections.singleton(-2));
	        classicalChannel.sendMessage(new Message(ClassicalMessageType.SIFT_DONE, bitIndex,null));
	}
        System.out.println("Bob   [C]: " + m);
    }

    public void sendClassical(final Message m) {
	this.classicalChannel.sendMessage(m);
    }

    public void start() {
        Thread t = new Thread(){
	    @Override public void run() {
		while(true){

		}
	    }
	};
        t.start();
    }


    @Override public void receive(final Transmittable m) {
	System.out.println("BOB RECIEVE: " + m);
    }
}

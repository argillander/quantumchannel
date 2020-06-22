import java.util.Random;

public class Bob implements ChannelRecipient, ClassicalChannelRecipient
{

    FilterSetting[] basis; // = {FilterSetting.HV, FilterSetting.PM, FilterSetting.PM, FilterSetting.HV, FilterSetting.PM };
    int basisPointer = 0;
    Random rand;
    int n_bits = 0;
    ClassicalChannel classicalChannel;

    public Bob(final int n_bits, final ClassicalChannel classicalChannel) {
	this.n_bits = n_bits;
	this.classicalChannel = classicalChannel;
	this.rand = new Random();
	basis = new FilterSetting[n_bits];
	selectBases();
    }

    @Override public PolarizationQubit receiveQubit(final PolarizationQubit qb) {
        PolarizationQubit recv;
        PolarizationFilter pf = new PolarizationFilter(basis[basisPointer]);
        basisPointer++;

        recv = pf.filterQubit(qb);
	System.out.println("Bob:   " + recv.getPolarization() + System.lineSeparator());
	return qb;
    }

    @Override public PolarizationQubit sendQubit(final PolarizationQubit qb) {
	return null;
    }

    private void selectBases(){
        FilterSetting randSett;
	for (int i = 0; i < this.n_bits; i++) {
	    randSett = FilterSetting.values()[rand.nextInt(FilterSetting.values().length)];
	    basis[i] = randSett;
	}
	System.out.println("Bob: Allocated bases");
	printBases();
    }

    private void printBases(){
	System.out.println("Bob: Basis selection ");
	for (int i = 0; i <n_bits ; i++) {
	    System.out.print(basis[i] + " ");
	}
	System.out.println(System.lineSeparator());
    }


    @Override public void receiveClassical(final Message m) {
	System.out.println("Bob [C]: " + m);
    }

    @Override public void sendClassical(final Message m) {
	this.classicalChannel.sendMessage(this,m);
    }
}

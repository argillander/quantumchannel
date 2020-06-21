import java.util.Random;

public class Bob implements ChannelRecipient
{

    FilterSetting[] basis; // = {FilterSetting.HV, FilterSetting.PM, FilterSetting.PM, FilterSetting.HV, FilterSetting.PM };
    int basisPointer = 0;
    Random rand;
    int n_bits = 0;

    public Bob(final int n_bits) {
	this.n_bits = n_bits;
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


}

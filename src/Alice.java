public class Alice
{
    Channel ch;

    int[] rawkey;
    int[] basisSelection;
    int n_bits;

    public Alice(final int n_bits, final Channel ch) {
	this.ch = ch;
    }

    PolarizationQubit sendQubit(){
        PolarizationQubit qb = new PolarizationQubit(Polarization.V);
        System.out.println("Alice: " + qb.getPolarization());

        ch.send(qb);
        return qb;
    }
}

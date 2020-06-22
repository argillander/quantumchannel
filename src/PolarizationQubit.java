import java.util.Random;

public class PolarizationQubit extends Qubit
{
    static Random rand;

    private Polarization polarization = Polarization.UNSET;

    public Polarization getPolarization() {
	return polarization;
    }

    public void setPolarization(final Polarization polarization) {
	this.polarization = polarization;
    }

    public PolarizationQubit(final Polarization p) {
	this.polarization = p;
    }

    public PolarizationQubit() {
	// Initialize with random polarization
        Polarization p;
        p = Polarization.values()[rand.nextInt(Polarization.values().length -1)];


    }

    @Override public String toString() {
	return "PolarizationQubit{" + "polarization=" + polarization + '}';
    }
}

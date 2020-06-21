public class PolarizationQubit extends Qubit
{

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


    @Override public String toString() {
	return "PolarizationQubit{" + "polarization=" + polarization + '}';
    }
}

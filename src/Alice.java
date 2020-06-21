public class Alice
{
    Channel ch;

    public Alice(final Channel ch) {
	this.ch = ch;
    }

    void sendQubit(){
        Qubit qb = new PolarizationQubit(Polarization.V);
        ch.send(qb);
    }
}

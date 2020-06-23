public abstract class Qubit implements Transmittable
{
    String value = "";

    public Qubit() {
    }


    @Override public String toString() {
	return "Qubit{" + "value='" + value + '\'' + '}';
    }
}

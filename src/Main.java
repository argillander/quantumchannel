public class Main
{
    public static void main(String[] args) {
        System.out.println("Started QSim");
        LosslessQuantumChannel ch = new LosslessQuantumChannel();
        Bob b = new Bob();
        ch.addRecipient(b);
        Alice a = new Alice(ch);

        a.sendQubit();

    }


}

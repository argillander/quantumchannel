public class Main
{
    public static void main(String[] args) {
        int N_BITS = 5;


        System.out.println("Started QSim");
        LosslessQuantumChannel ch = new LosslessQuantumChannel();
        Bob b = new Bob(N_BITS);
        ch.addRecipient(b);
        Alice a = new Alice(N_BITS, ch);

        for (int i = 0; i < 5; i++) {
          a.sendQubit();
        }

    }


}

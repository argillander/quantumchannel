public class Main
{
    public static void main(String[] args) {
        int N_BITS = 5;


        System.out.println("Started QSim");
        LosslessQuantumChannel qCh = new LosslessQuantumChannel();
        ClassicalChannel cCh = new ClassicalChannel();
        Bob b = new Bob(N_BITS, cCh);
        qCh.addRecipient(b);
        Alice a = new Alice(N_BITS, qCh, cCh);
        cCh.addRecipient(b);
        cCh.addRecipient(a);

        a.sendClassical(new Message(ClassicalMessageType.DEBUG, "Hello Bob!"));
        b.sendClassical(new Message(ClassicalMessageType.DEBUG, "Hello Alice!"));
    /*
        for (int i = 0; i < 5; i++) {
          a.sendQubit();
        }
*/
    }


}

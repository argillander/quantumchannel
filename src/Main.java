public class Main
{
    public static void main(String[] args) {
        int N_BITS = 20;


        System.out.println("Started QSim");
        LosslessQuantumChannel qCh = new LosslessQuantumChannel();
        ClassicalChannel cCh = new ClassicalChannel();
        Bob b = new Bob(N_BITS, qCh, cCh);
        Alice a = new Alice(N_BITS, qCh, cCh);
        qCh.addRecipient(b);
        qCh.addRecipient(a);
        cCh.addRecipient(b);
        cCh.addRecipient(a);

        a.sendClassical(new Message(ClassicalMessageType.DEBUG, -1,"Hello Bob!"));
        b.sendClassical(new Message(ClassicalMessageType.DEBUG, -1,"Hello Alice!"));


        a.doQubitExchange();

        a.printBasis();
        b.printBasis();
        a.printFinalKey();
        b.printFinalKey();
        a.communicateBasisChoices();
        a.finalizeSift();

        a.printFinalKey();
        b.printFinalKey();

        //for (int i = 0; i < 5; i++) {
          //a.sendOneQubit();
       // }
    }


}

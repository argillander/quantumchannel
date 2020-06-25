public class Main
{

    final static int ALICE_CLASSIC = 5555;
    final static int BOB_CLASSIC = 5556;

    public static void main(String[] args) {
        int N_BITS = 2;


        System.out.println("Started QSim");
        LosslessQuantumChannel qCh = new LosslessQuantumChannel();


        Bob b = new Bob(N_BITS, qCh);
        b.start();

        Alice a = new Alice(N_BITS, qCh);
        qCh.addRecipient(b);
        qCh.addRecipient(a);

        // Setup classical
        ClassicalChannel cChA = new ClassicalChannel(a, ALICE_CLASSIC, BOB_CLASSIC);
        ClassicalChannel cChB = new ClassicalChannel(b, BOB_CLASSIC, ALICE_CLASSIC);
        a.setClassicalChannel(cChA);
        b.setClassicalChannel(cChB);
        // -----


        a.sendClassical(new Message(ClassicalMessageType.DEBUG, -1,"Hello Bob!"));
        b.sendClassical(new Message(ClassicalMessageType.DEBUG, -1,"Hello Alice!"));

    /*
        a.doQubitExchange();

        a.printBasis();
        b.printBasis();
        a.printFinalKey();
        b.printFinalKey();
        a.communicateBasisChoices();
        a.finalizeSift();

        a.printFinalKey();
        b.printFinalKey();
    */
        //for (int i = 0; i < 5; i++) {
          //a.sendOneQubit();
       // }
    }




}

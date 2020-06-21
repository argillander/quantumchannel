public class Bob implements ChannelRecipient
{
    @Override public void receiveQubit(final Qubit qb) {
	System.out.println("Received qubit " + qb);
    }
}

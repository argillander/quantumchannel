public interface ClassicalChannelRecipient extends ChannelRecipient
{
    void receiveClassical(Message m);

    void sendClassical(Message m);
}

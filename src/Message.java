public class Message
{
    private ClassicalMessageType messageType;
    private String message;
    private int bitIndex = 0;

    public Message(final ClassicalMessageType messageType, final int bitIndex, final String message) {
	this.messageType = messageType;
	this.message = message;
	this.bitIndex = bitIndex;
    }


    public int getBitIndex() {
	return bitIndex;
    }

    public ClassicalMessageType getMessageType() {
	return messageType;
    }

    public String getMessage() {
	return message;
    }

    public void setMessageType(final ClassicalMessageType messageType) {
	this.messageType = messageType;
    }

    public void setMessage(final String message) {
	this.message = message;
    }

    @Override public String toString() {
	return bitIndex + " "+  messageType  +  ": \"" + message + "\"";
    }
}

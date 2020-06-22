public class Message
{
    private ClassicalMessageType messageType;
    private String message;

    public Message(final ClassicalMessageType messageType, final String message) {
	this.messageType = messageType;
	this.message = message;
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
	return  messageType + ": \"" + message + "\"";
    }
}

package jClient.events;

public class ClientResetFailureEvent extends ClientBaseEvent {
    public ClientResetFailureEvent(String clientId) {
        super(clientId);
    }
}

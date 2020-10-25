package jClient.events;

public class ClientCreationFailureEventClient extends ClientBaseEvent {
    public ClientCreationFailureEventClient(String clientId){
        super(clientId);
    }
}

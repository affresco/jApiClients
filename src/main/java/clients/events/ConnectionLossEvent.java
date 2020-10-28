package clients.events;

public class ConnectionLossEvent extends ClientBaseEvent {
    public ConnectionLossEvent(String clientId){
        super(clientId);
    }
}

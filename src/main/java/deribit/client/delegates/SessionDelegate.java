package deribit.client.delegates;

import clients.models.messages.Message;
import clients.models.messages.MessageHandler;
import deribit.client.core.DeribitWebsocketClientCore;
import deribit.client.messages.SessionMessage;
import deribit.client.handlers.GenericMessageHandler;

public class SessionDelegate extends Delegate {


    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public SessionDelegate(DeribitWebsocketClientCore client) {
        super(client);
    }

    // ##################################################################
    // REQUIRED BY ABSTRACT BASE CLASS
    // ##################################################################

    @Override
    public MessageHandler getHandler() {

        // Eventually, look at this...
        // https://github.com/google/gson

        return new GenericMessageHandler();
    }

    // ##################################################################
    // INTERFACE - CANCEL ON DISCONNECT
    // ##################################################################

    public String enableCancelOnDisconnect() throws InterruptedException {
        Message message = SessionMessage.enableCancelOnDisconnect();
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    public String disableCancelOnDisconnect() throws InterruptedException {
        Message message = SessionMessage.disableCancelOnDisconnect();
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    public String getCancelOnDisconnect() throws InterruptedException {
        Message message = SessionMessage.getCancelOnDisconnect();
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    // ##################################################################
    // INTERFACE - HEARTBEAT
    // ##################################################################

    public String enableHeartbeat() throws InterruptedException {
        Message message = SessionMessage.enableHeartbeat();
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }
    public String enableHeartbeat(int interval) throws InterruptedException {
        Message message = SessionMessage.enableHeartbeat(interval);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    public String disableHeartbeat() throws InterruptedException {
        Message message = SessionMessage.disableHeartbeat();
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }



}

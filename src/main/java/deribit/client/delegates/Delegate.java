package deribit.client.delegates;

import clients.models.messages.MessageHandler;
import deribit.client.core.DeribitWebsocketClient;

public abstract class Delegate {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    protected DeribitWebsocketClient client;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public Delegate(DeribitWebsocketClient client) {
        this.client = client;
    }

    // ##################################################################
    // ABSTRACT
    // ##################################################################

    public abstract MessageHandler getHandler();

}

package deribit.client.delegates;

import clients.models.messages.MessageHandler;
import deribit.client.core.DeribitWebsocketClientCore;

public abstract class Delegate {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    protected DeribitWebsocketClientCore client;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public Delegate(DeribitWebsocketClientCore client) {
        this.client = client;
    }

    // ##################################################################
    // ABSTRACT
    // ##################################################################

    public abstract MessageHandler getHandler();

}

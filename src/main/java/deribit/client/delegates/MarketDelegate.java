package deribit.client.delegates;

import clients.models.messages.MessageHandler;
import deribit.client.DeribitWebsocketClientCore;
import deribit.client.handlers.GenericMessageHandler;

public class MarketDelegate extends Delegate {

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public MarketDelegate(DeribitWebsocketClientCore client) {
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

}

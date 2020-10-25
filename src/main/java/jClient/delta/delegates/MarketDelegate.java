package jClient.delta.delegates;

import jClient.base.MessageHandler;
import jClient.delta.DeltaClientCore;
import jClient.delta.handlers.GenericMessageHandler;

public class MarketDelegate extends Delegate {

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public MarketDelegate(DeltaClientCore client) {
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

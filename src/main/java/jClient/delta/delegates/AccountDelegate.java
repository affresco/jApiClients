package jClient.delta.delegates;

import jClient.base.Message;
import jClient.base.MessageHandler;
import jClient.delta.DeltaClientCore;
import jClient.delta.factories.AccountMessage;
import jClient.delta.handlers.GenericMessageHandler;

public class AccountDelegate extends Delegate {

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public AccountDelegate(DeltaClientCore client) {
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
    // INTERFACE
    // ##################################################################

    public String getPosition(String instrument) throws InterruptedException {
        Message message = AccountMessage.getPosition(instrument);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }


}

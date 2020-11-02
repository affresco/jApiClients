package deribit.client.delegates;

import clients.models.messages.Message;
import clients.models.messages.MessageHandler;
import deribit.client.core.DeribitWebsocketClient;
import deribit.client.factories.AccountMessage;
import deribit.client.handlers.GenericMessageHandler;

public class AccountDelegate extends Delegate {

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public AccountDelegate(DeribitWebsocketClient client) {
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

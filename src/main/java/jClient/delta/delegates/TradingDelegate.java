package jClient.delta.delegates;

import jClient.base.Message;
import jClient.base.MessageHandler;
import jClient.delta.DeltaClientCore;
import jClient.delta.factories.TradingMessage;
import jClient.delta.handlers.GenericMessageHandler;

public class TradingDelegate extends Delegate {

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public TradingDelegate(DeltaClientCore client) {
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

    public String buyAtMarket(String instrument, Float amount, String label) throws InterruptedException {
        Message message = TradingMessage.buyAtMarket(instrument, amount, label);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    public String sellAtMarket(String instrument, Float amount, String label) throws InterruptedException {
        Message message = TradingMessage.sellAtMarket(instrument, amount, label);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    public String buyAtLimit(String instrument, Float amount, String label, Float limit) throws InterruptedException {
        Message message = TradingMessage.buyAtLimit(instrument, amount, label, limit);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    public String sellAtLimit(String instrument, Float amount, String label, Float limit) throws InterruptedException {
        Message message = TradingMessage.sellAtLimit(instrument, amount, label, limit);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    public String buyAtStopMarket(String instrument, Float amount, String label, Float stop) throws InterruptedException {
        Message message = TradingMessage.buyAtStopMarket(instrument, amount, label, stop);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    public String sellAtStopMarket(String instrument, Float amount, String label, Float stop) throws InterruptedException {
        Message message = TradingMessage.sellAtStopMarket(instrument, amount, label, stop);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    public String buyAtStopLimit(String instrument, Float amount, String label, Float limit, Float stop) throws InterruptedException {
        Message message = TradingMessage.buyAtStopLimit(instrument, amount, label, limit, stop);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    public String sellAtStopLimit(String instrument, Float amount, String label, Float limit, Float stop) throws InterruptedException {
        Message message = TradingMessage.sellAtStopLimit(instrument, amount, label, limit, stop);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

}

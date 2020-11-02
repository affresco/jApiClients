package deribit.client.delegates;

import clients.models.messages.Message;
import clients.models.messages.MessageHandler;
import deribit.client.core.DeribitWebsocketClient;
import deribit.client.factories.OrderMessage;
import deribit.client.handlers.GenericMessageHandler;


public class OrdersDelegate extends Delegate {

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public OrdersDelegate(DeribitWebsocketClient client) {
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
    // INTERFACE: OPEN ORDERS BY INSTRUMENT
    // ##################################################################

    public String getOpenOrdersByInstrument(String instrument) throws InterruptedException {
        Message message = OrderMessage.getOpenOrdersByInstrument(instrument);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    // ##################################################################
    // INTERFACE: OPEN ORDERS BY CURRENCY
    // ##################################################################

    public String getOpenOrdersByCurrency(String currency) throws InterruptedException {
        Message message = OrderMessage.getOpenOrdersByCurrency(currency);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }


    // ##################################################################
    // INTERFACE: HISTORICAL ORDERS BY INSTRUMENT
    // ##################################################################

    public String getHistoricalOrdersByInstrument(String instrument) throws InterruptedException {
        Message message = OrderMessage.getHistoricalOrdersByInstrument(instrument);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    // ##################################################################
    // INTERFACE: HISTORICAL ORDERS BY CURRENCY
    // ##################################################################

    public String getHistoricalOrdersByCurrency(String currency) throws InterruptedException {
        Message message = OrderMessage.getHistoricalOrdersByCurrency(currency);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    // ##################################################################
    // INTERFACE: CANCEL ORDERS
    // ##################################################################

    public String cancelByOrderId(String orderId) throws InterruptedException {
        Message message = OrderMessage.cancelByOrderId(orderId);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    public String cancelByLabel(String label) throws InterruptedException {
        Message message = OrderMessage.cancelByLabel(label);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }


    // ##################################################################
    // INTERFACE: CANCEL ALL ORDERS
    // ##################################################################

    public String cancelAll() throws InterruptedException {
        Message message = OrderMessage.cancelAll();
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }


    // ##################################################################
    // INTERFACE: CANCEL BY CURRENCY
    // ##################################################################

    public String cancelAllByCurrency(String currency) throws InterruptedException {
        Message message = OrderMessage.cancelAllByCurrency(currency);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    public String cancelAllByCurrency(String currency, String kind) throws InterruptedException {
        Message message = OrderMessage.cancelAllByCurrency(currency, kind);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    public String cancelAllByCurrency(String currency, String kind, String orderType) throws InterruptedException {
        Message message = OrderMessage.cancelAllByCurrency(currency, kind, orderType);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    // ##################################################################
    // INTERFACE: CANCEL BY INSTRUMENT
    // ##################################################################

    public String cancelAllByInstrument(String currency) throws InterruptedException {
        Message message = OrderMessage.cancelAllByInstrument(currency);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

    public String cancelAllByInstrument(String currency, String orderType) throws InterruptedException {
        Message message = OrderMessage.cancelAllByInstrument(currency, orderType);
        MessageHandler handler = getHandler();
        return client.makeRequest(message, handler);
    }

}









































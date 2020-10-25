package jClient.delta.factories;

import jClient.base.Message;
import jClient.delta.DeltaMessageFactory;
import jClient.delta.endpoints.TradingEndpoints;
import org.json.JSONObject;

public class OrderMessage {

    // ##################################################################
    // OPEN ORDERS -- BY INSTRUMENT
    // ##################################################################

    public static Message getOpenOrdersByInstrument(String instrument) {
        String method = TradingEndpoints.OPEN_ORDERS_BY_INSTRUMENT.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("instrument_name", instrument.toUpperCase());
        return DeltaMessageFactory.buildMessage(method, params);
    }


    // ##################################################################
    // OPEN ORDERS -- BY CURRENCY
    // ##################################################################

    public static Message getOpenOrdersByCurrency(String currency) {
        String method = TradingEndpoints.OPEN_ORDERS_BY_CURRENCY.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("currency", currency.toUpperCase());
        return DeltaMessageFactory.buildMessage(method, params);
    }


    // ##################################################################
    // HISTORICAL ORDERS -- BY INSTRUMENT
    // ##################################################################

    public static Message getHistoricalOrdersByInstrument(String instrument) {
        String method = TradingEndpoints.USER_ORDER_HISTORY_BY_INSTRUMENT.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("instrument_name", instrument.toUpperCase());
        return DeltaMessageFactory.buildMessage(method, params);
    }


    // ##################################################################
    // HISTORICAL ORDERS -- BY CURRENCY
    // ##################################################################

    public static Message getHistoricalOrdersByCurrency(String currency) {
        String method = TradingEndpoints.USER_ORDER_HISTORY_BY_CURRENCY.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("currency", currency.toUpperCase());
        return DeltaMessageFactory.buildMessage(method, params);
    }

    // ##################################################################
    // CANCEL ORDERS
    // ##################################################################

    public static Message cancelByOrderId(String orderId) {
        String method = TradingEndpoints.CANCEL_BY_ID.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("order_id", orderId);
        return DeltaMessageFactory.buildMessage(method, params);
    }

    public static Message cancelByLabel(String label) {
        String method = TradingEndpoints.CANCEL_BY_LABEL.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("label", label);
        return DeltaMessageFactory.buildMessage(method, params);
    }

    // ##################################################################
    // CANCEL ALL ORDERS
    // ##################################################################

    public static Message cancelAll() {
        String method = TradingEndpoints.CANCEL_ALL.getEndpoint();
        JSONObject params = new JSONObject();
        return DeltaMessageFactory.buildMessage(method, params);
    }

    // ##################################################################
    // CANCEL ORDERS BY CURRENCY
    // ##################################################################

    public static Message cancelAllByCurrency(String currency) {
        String method = TradingEndpoints.CANCEL_ALL_BY_CURRENCY.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("currency", currency.toUpperCase());
        return DeltaMessageFactory.buildMessage(method, params);
    }

    public static Message cancelAllByCurrency(String currency, String kind) {
        String method = TradingEndpoints.CANCEL_ALL_BY_CURRENCY.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("currency", currency.toUpperCase());
        params.put("kind", kind.toLowerCase());
        return DeltaMessageFactory.buildMessage(method, params);
    }

    public static Message cancelAllByCurrency(String currency, String kind, String orderType) {
        String method = TradingEndpoints.CANCEL_ALL_BY_CURRENCY.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("currency", currency.toUpperCase());
        params.put("kind", kind.toLowerCase());
        params.put("type", orderType.toLowerCase());
        return DeltaMessageFactory.buildMessage(method, params);
    }

    // ##################################################################
    // CANCEL ORDERS BY INSTRUMENT
    // ##################################################################

    public static Message cancelAllByInstrument(String instrument) {
        String method = TradingEndpoints.CANCEL_ALL_BY_INSTRUMENT.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("instrument", instrument.toUpperCase());
        return DeltaMessageFactory.buildMessage(method, params);
    }

    public static Message cancelAllByInstrument(String instrument, String orderType) {
        String method = TradingEndpoints.CANCEL_ALL_BY_INSTRUMENT.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("instrument", instrument.toUpperCase());
        params.put("type", orderType.toLowerCase());
        return DeltaMessageFactory.buildMessage(method, params);
    }

}

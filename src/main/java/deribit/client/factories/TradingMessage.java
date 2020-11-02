package deribit.client.factories;

import clients.models.messages.Message;
import deribit.client.endpoints.TradingEndpoints;
import org.json.JSONObject;

public class TradingMessage extends MessageFactory {

    // ##################################################################
    // TRADING - GENERICS
    // ##################################################################

    private static Message trade(String method, String instrument, Float amount, String label, String orderType, Float limit, Float stop) {

        // Common for all types
        JSONObject params = new JSONObject();
        params.put("instrument_name", instrument.toUpperCase());
        params.put("amount", amount);
        params.put("type", orderType);
        params.put("label", label);

        // Limit orders (limit and stop limit)
        if (limit != null) {
            params.put("price", limit);
        }

        // Stop orders (stop market and stop limit)
        if (stop != null) {
            params.put("stop_price", stop);
        }

        return buildMessage(method, params);
    }

    // ##################################################################
    // TRADING - AT MARKET
    // ##################################################################

    public static Message buyAtMarket(String instrument, Float amount, String label) {
        String method = TradingEndpoints.BUY.getEndpoint();
        return trade(method, instrument, amount, label, "market", null, null);
    }

    public static Message sellAtMarket(String instrument, Float amount, String label) {
        String method = TradingEndpoints.SELL.getEndpoint();
        return trade(method, instrument, amount, label, "market", null, null);
    }

    // ##################################################################
    // TRADING - AT LIMIT
    // ##################################################################

    public static Message buyAtLimit(String instrument, Float amount, String label, Float limit) {
        String method = TradingEndpoints.BUY.getEndpoint();
        return trade(method, instrument, amount, label, "limit", limit, null);
    }

    public static Message sellAtLimit(String instrument, Float amount, String label, Float limit) {
        String method = TradingEndpoints.SELL.getEndpoint();
        return trade(method, instrument, amount, label, "limit", limit, null);
    }

    // ##################################################################
    // TRADING - AT STOP MARKET
    // ##################################################################

    public static Message buyAtStopMarket(String instrument, Float amount, String label, Float stop) {
        String method = TradingEndpoints.BUY.getEndpoint();
        return trade(method, instrument, amount, label, "stop_market", null, stop);
    }

    public static Message sellAtStopMarket(String instrument, Float amount, String label, Float stop) {
        String method = TradingEndpoints.SELL.getEndpoint();
        return trade(method, instrument, amount, label, "stop_market", null, stop);
    }

    // ##################################################################
    // TRADING - AT STOP LIMIT
    // ##################################################################

    public static Message buyAtStopLimit(String instrument, Float amount, String label, Float limit, Float stop) {
        String method = TradingEndpoints.BUY.getEndpoint();
        // TODO: Insert some validation for the stop being greater than the limit
        return trade(method, instrument, amount, label, "stop_limit", limit, stop);
    }

    public static Message sellAtStopLimit(String instrument, Float amount, String label, Float limit, Float stop) {
        String method = TradingEndpoints.SELL.getEndpoint();
        // TODO: Insert some validation for the stop being smaller than the limit
        return trade(method, instrument, amount, label, "stop_limit", limit, stop);
    }

}

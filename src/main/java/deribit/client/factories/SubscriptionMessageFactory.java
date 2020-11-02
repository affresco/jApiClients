package deribit.client.factories;

import clients.models.messages.Message;
import deribit.client.endpoints.SubscriptionEndpoints;
import org.json.JSONObject;
import utilities.Identity;

import java.util.ArrayList;
import deribit.client.models.SubscriptionMessage;

public class SubscriptionMessageFactory extends MessageFactory {

    // ##################################################################
    // QUOTES
    // ##################################################################

    public static SubscriptionMessage subscribeQuotes(String instrument) {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toQuoteChannel(instrument));
        return publicSubscribe(channels);
    }

    public static SubscriptionMessage unsubscribeQuotes(String instrument) {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toQuoteChannel(instrument));
        return publicUnsubscribe(channels);
    }

    public static String toQuoteChannel(String instrument) {
        return "quote." + instrument.toUpperCase() ;
    }

    // ##################################################################
    // ORDER BOOKS
    // ##################################################################

    public static SubscriptionMessage subscribeBooks(String instrument) {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toBooksChannel(instrument));
        return publicSubscribe(channels);
    }

    public static SubscriptionMessage unsubscribeBooks(String instrument) {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toBooksChannel(instrument));
        return publicUnsubscribe(channels);
    }

    public static String toBooksChannel(String instrument) {
        return "book." + instrument.toLowerCase()  + "10.100ms";
    }

    // ##################################################################
    // HOME MADE INDEX
    // ##################################################################

    public static SubscriptionMessage subscribeIndex(String currency) {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toIndexChannel(currency));
        return publicSubscribe(channels);
    }

    public static SubscriptionMessage unsubscribeIndex(String currency) {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toIndexChannel(currency));
        return publicUnsubscribe(channels);
    }

    public static String toIndexChannel(String currency) {
        return "deribit_price_index." + currency.toLowerCase() + "_usd";
    }

    // ##################################################################
    // COMMONS
    // ##################################################################

    private static SubscriptionMessage publicSubscribe(ArrayList<String> channels) {
        String method = SubscriptionEndpoints.PUBLIC_SUBSCRIBE.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("channels", channels);
        return buildMessage(method, params, channels);
    }

    private static SubscriptionMessage publicUnsubscribe(ArrayList<String> channels) {
        String method = SubscriptionEndpoints.PUBLIC_UNSUBSCRIBE.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("channels", channels);
        return buildMessage(method, params, channels);
    }

    public static SubscriptionMessage buildMessage(String endpoint, JSONObject params, ArrayList<String> channels) {
        String id = Identity.generateId();
        return buildMessage(endpoint, params, id, channels);
    }

    public static SubscriptionMessage buildMessage(String endpoint, JSONObject params, String id, ArrayList<String> channels) {

        // Basic wrapper for Delta exchange
        JSONObject content = new JSONObject();

        // From the docs
        content.put("jsonrpc", "2.0");
        content.put("id", id);

        // Endpoint-like string for websocket
        content.put("method", endpoint);

        // Add params (content) to wrapper
        content.put("params", params);

        // Return
        return new deribit.client.models.SubscriptionMessage(content, id, channels);
    }


}

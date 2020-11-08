package deribit.client.factories;

import clients.models.messages.Message;
import commons.standards.InstrumentKind;
import deribit.client.endpoints.SubscriptionEndpoints;
import org.json.JSONObject;
import utilities.Identity;

import java.util.ArrayList;
import java.util.List;

import deribit.client.models.SubscriptionMessage;

public class SubscriptionMessageFactory extends MessageFactory {

    // ##################################################################
    // QUOTES (PUBLIC)
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
        return "quote." + instrument;
    }

    // ##################################################################
    // TRADES (PUBLIC)
    // ##################################################################

    public static SubscriptionMessage subscribeTrades(String instrument) {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toTradeChannel(instrument));
        return publicSubscribe(channels);
    }

    public static SubscriptionMessage unsubscribeTrades(String instrument) {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toTradeChannel(instrument));
        return publicUnsubscribe(channels);
    }

    public static String toTradeChannel(String instrument) {
        return "trades." + instrument ;
    }

    // ##################################################################
    // ORDER BOOKS (PUBLIC)
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
        // TODO; Check if this works with none...
        // return "book." + instrument + "10.100ms";
        return "book." + instrument + "none.100ms";
    }

    // ##################################################################
    // DERIBIT INDEX (PUBLIC)
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
    // PLATFORM STATE (PUBLIC)
    // ##################################################################

    public static SubscriptionMessage subscribePlatformState() {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toPlatformStateChannel());
        return publicSubscribe(channels);
    }

    public static SubscriptionMessage unsubscribePlatformState() {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toPlatformStateChannel());
        return publicUnsubscribe(channels);
    }

    public static String toPlatformStateChannel() {
        return "platform_state";
    }

    // ##################################################################
    // USER ORDERS (PRIVATE)
    // ##################################################################

    public static SubscriptionMessage subscribeUserOrders() {
        ArrayList<String> channels = new ArrayList<>(List.of(toUserOrdersChannel()));
        return privateSubscribe(channels);
    }

    public static SubscriptionMessage subscribeUserOrders(String instrument) {
        ArrayList<String> channels = new ArrayList<>(List.of(toUserOrdersChannel(instrument)));
        return privateSubscribe(channels);
    }

    public static SubscriptionMessage subscribeUserOrders(String kind, String currency) {
        ArrayList<String> channels = new ArrayList<>(List.of(toUserOrdersChannel(kind, currency)));
        return privateSubscribe(channels);
    }

    public static SubscriptionMessage unsubscribeUserOrders() {
        ArrayList<String> channels = new ArrayList<>(List.of(toUserOrdersChannel()));
        return privateUnsubscribe(channels);
    }

    public static SubscriptionMessage unsubscribeUserOrders(String instrument) {
        ArrayList<String> channels = new ArrayList<>(List.of(toUserOrdersChannel(instrument)));
        return privateUnsubscribe(channels);
    }

    public static SubscriptionMessage unsubscribeUserOrders(String kind, String currency) {
        ArrayList<String> channels = new ArrayList<>(List.of(toUserOrdersChannel(kind, currency)));
        return privateUnsubscribe(channels);
    }

    public static String toUserOrdersChannel(String instrument) {
        return "user.orders." + instrument + ".raw";
    }

    public static String toUserOrdersChannel(String kind, String currency) {
        return "user.orders." + kind + "." + currency + ".raw";
    }

    public static String toUserOrdersChannel() {
        return "user.orders.any.any.raw";
    }
    
    // ##################################################################
    // USER TRADES (PRIVATE)
    // ##################################################################

    public static SubscriptionMessage subscribeUserTrades() {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toUserTradesChannel());
        return privateSubscribe(channels);
    }

    public static SubscriptionMessage subscribeUserTrades(String instrument) {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toUserTradesChannel(instrument));
        return privateSubscribe(channels);
    }

    public static SubscriptionMessage subscribeUserTrades(String kind, String currency) {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toUserTradesChannel(kind, currency));
        return privateSubscribe(channels);
    }

    public static SubscriptionMessage unsubscribeUserTrades() {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toUserTradesChannel());
        return privateUnsubscribe(channels);
    }

    public static SubscriptionMessage unsubscribeUserTrades(String instrument) {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toUserTradesChannel(instrument));
        return privateUnsubscribe(channels);
    }

    public static SubscriptionMessage unsubscribeUserTrades(String kind, String currency) {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toUserTradesChannel(kind, currency));
        return privateUnsubscribe(channels);
    }

    public static String toUserTradesChannel(String instrument) {
        return "user.trades." + instrument + ".raw";
    }

    public static String toUserTradesChannel(String kind, String currency) {
        return "user.trades." + kind + "." + currency + ".raw";
    }

    public static String toUserTradesChannel() {
        return "user.trades.any.any.raw";
    }

    // ##################################################################
    // USER PORTFOLIO (PRIVATE)
    // ##################################################################

    public static SubscriptionMessage subscribePortfolio(String currency) {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toIndexChannel(currency));
        return privateSubscribe(channels);
    }

    public static SubscriptionMessage unsubscribePortfolio(String currency) {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toIndexChannel(currency));
        return privateUnsubscribe(channels);
    }

    public static String toPortfolioChannel(String currency) {
        return "user.portfolio." + currency;
    }

    // ##################################################################
    // MMP TRIGGER (PRIVATE)
    // ##################################################################

    public static SubscriptionMessage subscribeMmpTrigger(String currency) {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toIndexChannel(currency));
        return privateSubscribe(channels);
    }

    public static SubscriptionMessage unsubscribeMmpTrigger(String currency) {
        ArrayList<String> channels = new ArrayList<>();
        channels.add(toIndexChannel(currency));
        return privateUnsubscribe(channels);
    }

    public static String toMmpTriggerChannel(String currency) {
        return "user.mmp_trigger." + currency;
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

    private static SubscriptionMessage privateSubscribe(ArrayList<String> channels) {
        String method = SubscriptionEndpoints.PRIVATE_SUBSCRIBE.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("channels", channels);
        return buildMessage(method, params, channels);
    }

    private static SubscriptionMessage privateUnsubscribe(ArrayList<String> channels) {
        String method = SubscriptionEndpoints.PRIVATE_UNSUBSCRIBE.getEndpoint();
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

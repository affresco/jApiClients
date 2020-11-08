package apps.market.feeds;

import apps.market.Settings;
import apps.market.events.DeribitIndexEvent;
import apps.market.events.DeribitQuoteEvent;
import apps.market.logging.MarketAppLogger;
import apps.market.models.quotes.DeribitQuote;
import apps.market.models.quotes.DeribitQuoteFactory;
import clients.models.messages.Message;
import commons.standards.Cryptocurrency;
import commons.standards.InstrumentKind;
import deribit.client.DeribitCredentials;
import deribit.client.core.DeribitWebsocketClient;
import deribit.client.factories.SubscriptionMessageFactory;
import deribit.client.models.SubscriptionMessage;
import org.apache.log4j.BasicConfigurator;
import org.greenrobot.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DeribitDataFeed extends DeribitWebsocketClient {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    // Channel management
    private final HashMap<String, SubscriptionMessage> channels;
    private final HashMap<String, SubscriptionMessage> channelsBeingAdded;
    private final HashMap<String, SubscriptionMessage> channelsBeingRemoved;

    // Logging via SLF4J
    private static Logger logger;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public DeribitDataFeed(String url, DeribitCredentials credentials, String clientId) throws InterruptedException, URISyntaxException {

        // Invoke super class constructor first
        super(url, credentials, clientId);

        // Setup logging
        logger = getLogger();

        // Init all channel structures
        this.channels = new HashMap<>();
        this.channelsBeingAdded = new HashMap<>();
        this.channelsBeingRemoved = new HashMap<>();

        // Connect and block while doing it
        this.connectBlocking(2000, TimeUnit.SECONDS);

    }

    // ##################################################################
    // LOGGING
    // ##################################################################

    protected static Logger getLogger() {
        if(logger == null){
            logger = LoggerFactory.getLogger(Settings.SYMBOL.toString());
            BasicConfigurator.configure();
        }
        return logger;
    }

    // ##################################################################
    // MAKING A REQUEST WITHOUT THE HANDLER
    // ##################################################################

    public void makeRequest(Message message) {
        this.send(message.getContent());
    }

    // ##################################################################
    // INTERFACE OVERRIDES
    // ##################################################################

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
        try {
            // Quote
            if (message.contains("quote")) {
                broadcastQuote(message);
            }

            // Book
            else if (message.contains("book")) {
                broadcastBook(message);
            }

            // Index level
            else if (message.contains("index")) {
                broadcastIndex(message);
            }

            // Probably a system message
            else {
                parseSystemMessage(message);
            }

        } catch (Exception e) {
            parseSystemMessage(message);
        }
    }

    // ##################################################################
    // EVENTS PUBLISHING METHODS
    // ##################################################################

    public void broadcastQuote(String message) {
        try {
            DeribitQuote quote = DeribitQuoteFactory.fromQuoteSubscriptionMessage(message);
            EventBus.getDefault().postSticky(new DeribitQuoteEvent(quote));
        } catch (Exception e) {
            MarketAppLogger.error("Broadcasting quote failed", e);
        }
    }

    public void broadcastIndex(String message) {
        try {
            DeribitQuote quote = DeribitQuoteFactory.fromIndexSubscriptionMessage(message);
            EventBus.getDefault().postSticky(new DeribitIndexEvent(quote));
        } catch (Exception e) {
            MarketAppLogger.error("Broadcasting index failed", e);
        }
    }

    public void broadcastBook(String message) {
        System.out.println("Book:" + message);
    }


    // ##################################################################
    // SYSTEM/CONNECTION EVENTS
    // ##################################################################

    public void parseSystemMessage(String message) {
        System.out.println("System Message:" + message);
    }

    // ##################################################################
    // INTERFACE -- SUBSCRIPTION
    // ##################################################################

    public void subscribeQuotes(String instrument) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.subscribeQuotes(instrument);

        // Log
        logger.warn("Subscribing Deribit quotes data for instrument: " + instrument.toString());

        // Un-/Subscribe all pending channels
        subscribe(msg);
    }

    public void subscribeTrades(String instrument) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.subscribeQuotes(instrument);

        // Log
        logger.warn("Subscribing Deribit trades data for instrument: " + instrument.toString());

        // Un-/Subscribe all pending channels
        subscribe(msg);
    }

    public void subscribeIndex(Cryptocurrency currency) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.subscribeIndex(currency.toString());

        // Log
        logger.warn("Subscribing Deribit index data for currency: " + currency.toString());

        // Un-/Subscribe all pending channels
        subscribe(msg);
    }

    public void subscribeBook(String instrument) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.subscribeBooks(instrument);

        // Un-/Subscribe all pending channels
        subscribe(msg);
    }

    public void subscribePlatformState() {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.subscribePlatformState();

        // Un-/Subscribe all pending channels
        subscribe(msg);
    }

    public void subscribeUserOrders() {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.subscribeUserOrders();

        // Un-/Subscribe all pending channels
        subscribe(msg);
    }

    public void subscribeUserOrders(String instrument) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.subscribeUserOrders(instrument);

        // Un-/Subscribe all pending channels
        subscribe(msg);
    }

    public void subscribeUserOrders(InstrumentKind kind, Cryptocurrency currency) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.subscribeUserOrders(kind.toString(), currency.toString());

        // Un-/Subscribe all pending channels
        subscribe(msg);
    }

    public void subscribeUserTrades() {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.subscribeUserTrades();

        // Un-/Subscribe all pending channels
        subscribe(msg);
    }

    public void subscribeUserTrades(String instrument) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.subscribeUserTrades(instrument);

        // Un-/Subscribe all pending channels
        subscribe(msg);
    }

    public void subscribeUserTrades(InstrumentKind kind, Cryptocurrency currency) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.subscribeUserTrades(kind.toString(), currency.toString());

        // Un-/Subscribe all pending channels
        subscribe(msg);
    }

    public void subscribeUserPortfolio(Cryptocurrency currency) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.subscribePortfolio(currency.toString());

        // Un-/Subscribe all pending channels
        subscribe(msg);
    }

    // ##################################################################
    // INTERFACE -- UN-SUBSCRIPTION
    // ##################################################################

    public void unsubscribeQuotes(String instrument) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.unsubscribeQuotes(instrument);

        // Un-/Subscribe all pending channels
        unsubscribe(msg);
    }

    public void unsubscribeIndex(Cryptocurrency currency) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.unsubscribeIndex(currency.toString());

        // Un-/Subscribe all pending channels
        unsubscribe(msg);
    }

    public void unsubscribeBook(String instrument) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.unsubscribeBooks(instrument);

        // Un-/Subscribe all pending channels
        unsubscribe(msg);
    }

    public void unsubscribePlatformState() {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.unsubscribePlatformState();

        // Un-/Subscribe all pending channels
        unsubscribe(msg);
    }

    public void unsubscribeUserOrders() {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.unsubscribeUserOrders();

        // Un-/unsubscribe all pending channels
        unsubscribe(msg);
    }

    public void unsubscribeUserOrders(String instrument) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.unsubscribeUserOrders(instrument);

        // Un-/unsubscribe all pending channels
        unsubscribe(msg);
    }

    public void unsubscribeUserOrders(InstrumentKind kind, Cryptocurrency currency) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.unsubscribeUserOrders(kind.toString(), currency.toString());

        // Un-/unsubscribe all pending channels
        unsubscribe(msg);
    }

    public void unsubscribeUserTrades() {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.unsubscribeUserTrades();

        // Un-/unsubscribe all pending channels
        unsubscribe(msg);
    }

    public void unsubscribeUserTrades(String instrument) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.unsubscribeUserTrades(instrument);

        // Un-/unsubscribe all pending channels
        unsubscribe(msg);
    }

    public void unsubscribeUserTrades(InstrumentKind kind, Cryptocurrency currency) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.unsubscribeUserTrades(kind.toString(), currency.toString());

        // Un-/unsubscribe all pending channels
        unsubscribe(msg);
    }

    public void unsubscribeUserPortfolio(Cryptocurrency currency) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.unsubscribePortfolio(currency.toString());

        // Un-/unsubscribe all pending channels
        unsubscribe(msg);
    }

    // ##################################################################
    // CORE: KEEPING RECORDS UP-TO-DATE
    // ##################################################################

    private void addChannels(ArrayList<String> channels, SubscriptionMessage message) {
        synchronized (channelsBeingAdded) {
            synchronized (this.channels) {
                for (String s : channels) {
                    if (!this.channels.containsKey(s) && !channelsBeingAdded.containsKey(s)) {
                        channelsBeingAdded.put(s, message);
                    }
                }
            }
        }
    }

    private void removeChannels(ArrayList<String> channels, SubscriptionMessage message) {
        synchronized (channelsBeingRemoved) {
            synchronized (this.channels) {
                for (String s : channels) {
                    if (this.channels.containsKey(s) && !channelsBeingAdded.containsKey(s)) {
                        channelsBeingRemoved.put(s, message);
                    }
                }
            }
        }
    }

    // ##################################################################
    // CORE: SUBSCRIBE TO CHANNELS
    // ##################################################################

    private void subscribe(SubscriptionMessage message) {

        // Extract the channels
        ArrayList<String> channels = message.getChannels();

        // Make sure we keep a record
        // of channels that were requested
        addChannels(channels, message);

        // Subscribe all channels
        channelsBeingAdded.forEach((ch, msg) ->
        {
            makeRequest(msg);
            this.channels.put(ch, msg);
        });

        // Clear out the incoming channels list
        this.channels.forEach((ch, msg) -> {
            channelsBeingAdded.remove(ch);
        });
    }

    // ##################################################################
    // CORE: UNSUBSCRIBE TO CHANNELS
    // ##################################################################

    private void unsubscribe(SubscriptionMessage message) {

        // Extract the channels
        ArrayList<String> channels = message.getChannels();

        // Make sure we keep a record
        // of channels that were requested
        removeChannels(channels, message);

        // Subscribe all channels
        channelsBeingRemoved.forEach((ch, msg) ->
        {
            makeRequest(msg);
            this.channels.remove(ch);
        });

        // Clear out the incoming channels list
        this.channels.forEach((ch, msg) -> {
            channelsBeingRemoved.remove(ch);
        });
    }

    // ##################################################################
    // MAIN
    // ##################################################################

    public static void main(String[] args) throws URISyntaxException, InterruptedException {

        // This will be replaced in the future
        String key = "5Bt7EVi8";
        String secret = "zavOtsOIvTkAprQz1UKhz-35TxHOPIscMYgsAYzJFYY";
        String url = "wss://test.deribit.com/ws/api/v2";
        DeribitCredentials credentials = new DeribitCredentials(key, secret, url);

        // Make an instance
        String clientId = "N/A";
        DeribitDataFeed client = new DeribitDataFeed(url, credentials, clientId);

        System.out.println("Subscription client isOpen " + client.isOpen());
        client.subscribeQuotes("BTC-PERPETUAL");
        // client.subscribeIndex(Cryptocurrency.BTC);
        int j = 0;
        while (j < 600) {
            j++;
            Thread.sleep(1000);
        }


    }

}

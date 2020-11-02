package apps.market.feeds;

import apps.market.events.DeribitIndexEvent;
import apps.market.exceptions.InvalidMessageException;
import apps.market.models.quotes.DeribitQuote;
import apps.market.models.quotes.DeribitQuoteFactory;
import clients.models.messages.Message;
import deribit.client.DeribitCredentials;
import deribit.client.core.DeribitWebsocketClient;
import deribit.client.factories.SubscriptionMessageFactory;
import deribit.client.models.SubscriptionMessage;
import org.greenrobot.eventbus.EventBus;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DeribitDataFeed extends DeribitWebsocketClient {

    // ##################################################################
    // PROPERTIES
    // ##################################################################

    // Channel management
    private final HashMap<String, SubscriptionMessage> activeChannels;
    private final HashMap<String, SubscriptionMessage> incomingChannels;
    private final HashMap<String, SubscriptionMessage> outgoingChannels;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public DeribitDataFeed(String url, DeribitCredentials credentials, String clientId) throws InterruptedException, URISyntaxException {

        // Invoke super class constructor first
        super(url, credentials, clientId);

        // Init all channel structures
        this.activeChannels = new HashMap<>();
        this.incomingChannels = new HashMap<>();
        this.outgoingChannels = new HashMap<>();

        // Connect and block while doing it
        this.connectBlocking(2000, TimeUnit.SECONDS);

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
        }

        catch (Exception e) {
            parseSystemMessage(message);
        }

    }

    // ##################################################################
    // EVENTS PUBLISHING METHODS
    // ##################################################################

    public void broadcastQuote(String message) {
        try {
            DeribitQuote quote = DeribitQuoteFactory.fromSubscriptionMessage(message);
            EventBus.getDefault().postSticky(new DeribitIndexEvent(quote));
        }
        catch (Exception e){
            System.out.println("Some error message goes into the logs...");
        }
    }

    public void broadcastIndex(String message) {

    }

    public void broadcastBook(String message) {

    }

    public void parseSystemMessage(String message) {

    }


    // ##################################################################
    // INTERFACE
    // ##################################################################

    public void subscribeQuotes(String instrument) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.subscribeQuotes(instrument);

        // Un-/Subscribe all pending channels
        subscribe(msg);
    }

    public void subscribeIndex(String currency) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.subscribeIndex(currency);

        // Un-/Subscribe all pending channels
        subscribe(msg);
    }

    public void subscribeBook(String instrument) {

        // Convert to corresponding message
        SubscriptionMessage msg = SubscriptionMessageFactory.subscribeBooks(instrument);

        // Un-/Subscribe all pending channels
        subscribe(msg);
    }

    // ##################################################################
    // CORE: RECORD KEEPING
    // ##################################################################

    private void addToIncoming(ArrayList<String> channels, SubscriptionMessage message) {
        synchronized (incomingChannels) {
            for (String s : channels) {
                if (!activeChannels.containsKey(s) && !incomingChannels.containsKey(s)) {
                    incomingChannels.put(s, message);
                }
            }
        }
    }

    private void addToOutgoing(ArrayList<String> channels, SubscriptionMessage message) {
        synchronized (outgoingChannels) {
            for (String s : channels) {
                if (activeChannels.containsKey(s) && !incomingChannels.containsKey(s)) {
                    outgoingChannels.put(s, message);
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
        addToIncoming(channels, message);

        // Subscribe all channels
        incomingChannels.forEach((ch, msg) ->
        {
            makeRequest(msg);
            this.activeChannels.put(ch, msg);
        });

        // Clear out the incoming channels list
        this.activeChannels.forEach((ch, msg) -> {
            incomingChannels.remove(ch);
        });
    }

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
        int j = 0;
        while (j < 60) {
            j++;
            Thread.sleep(1000);
        }


    }

}

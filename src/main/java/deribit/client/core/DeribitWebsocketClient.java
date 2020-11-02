package deribit.client.core;

import clients.websocket.BaseWebsocketClient;
import clients.models.messages.Message;
import deribit.client.DeribitCredentials;
import deribit.client.factories.AuthenticationMessage;
import deribit.client.factories.SupportingMessage;

import deribit.client.handlers.GenericMessageHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import clients.models.messages.MessageHandler;

import java.net.URISyntaxException;

public class DeribitWebsocketClient extends BaseWebsocketClient {


    // ##################################################################
    // INSTANCE VARIABLES
    // ##################################################################

    private final String clientId;

    private int connectionTimeout = 1;
    private int connectionCheckPeriod = 5;
    private boolean blockOnConnection = true;
    private final TimeUnit timeUnit = TimeUnit.SECONDS;

    private final ConcurrentHashMap<String, String> messages = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, MessageHandler> messageHandlers = new ConcurrentHashMap<>();

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public DeribitWebsocketClient(String url, DeribitCredentials credentials, String clientId) throws URISyntaxException {
        this(url, credentials, clientId, true);
    }

    public DeribitWebsocketClient(String url, DeribitCredentials credentials, String clientId, boolean blockOnConnection) throws URISyntaxException {
        super(url, credentials);
        this.blockOnConnection = blockOnConnection;
        this.clientId = clientId;
    }

    // ##################################################################
    // BASE CLIENT INTERFACE IMPLEMENTATION
    // ##################################################################

    @Override
    public String getOnOpenMessage() {
        System.out.println("Delta client (core) providing authentication credentials.");
        Message login = AuthenticationMessage.login(credentials);
        return login.getContent();
    }

    // ##################################################################
    // INTERFACE
    // ##################################################################

    public void maybe_reconnect() throws InterruptedException {
        if (!this.isConnected()) {
            this.connectBlocking(connectionTimeout, timeUnit);
        }
    }

    public boolean isConnected() {
        if (!this.isOpen()) {
            return false;
        }
        return this.makeTestRequest();
    }

    public void addMessageHandler(String messageId, MessageHandler messageHandler) {
        this.messageHandlers.put(messageId, messageHandler);
    }

    public boolean makeTestRequest() {
        Message message = SupportingMessage.testWithoutException();
        MessageHandler handler = new GenericMessageHandler();
        try {
            String answer = makeRequest(message, handler);
            if (answer == null) {
                return false;
            }
            return !answer.contains("error");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public String makeRequest(Message message, MessageHandler messageHandler) throws InterruptedException {

        // Add the Handler to the local variable
        addMessageHandler(message.getMessageId(), messageHandler);

        // Make the request through the websocket
        String content = message.getContent();
        this.send(message.getContent());

        // Get the answer from the queue
        return waitBlockingForMessage(message.getMessageId(), 10, 5000);
    }

    public String waitBlockingForMessage(String messageId, int pollEveryMillis, int pollTimeoutMillis) throws InterruptedException {
        int waitedMillis = 0;
        while (waitedMillis <= pollTimeoutMillis) {
            waitedMillis += pollEveryMillis;

            if (this.messages.containsKey(messageId)) {

                // Retrieve relevant items
                String message = this.messages.get(messageId);
                MessageHandler handler = this.messageHandlers.get(messageId);

                // Free up the client
                this.messages.remove(messageId);
                this.messageHandlers.remove(messageId);

                return handler.handleMessage(message);
            }

            // Wait
            Thread.sleep(pollEveryMillis);
        }
        return null;
    }

    // ##################################################################
    // BASE CLASS OVERRIDE
    // ##################################################################

    // This is exactly that
    // https://stackoverflow.com/questions/40714942/java-websocket-messagehandler-return-to-global-scope

    @Override
    public void onMessage(String message) {

        System.out.println("received: " + message);

        String messageId = getIdFromMessage(message);
        if (messageId == null) {
            // this should return a generic callback (events?)
            return;
        }
        // Add to our local message for
        // the handler to pick-up later
        messages.put(messageId, message);
    }

    public String getIdFromMessage(String message) {

        // No message, nothing to do anyway
        if (message == null) {
            return null;
        }

        // Init variable
        String messageId = null;

        // Try to extract the id
        try {
            int idx = message.indexOf("id");
            if (idx != -1) {
                int start = idx + 5;
                int end = start + 8;
                messageId = message.substring(start, end);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return messageId;
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public String getClientId() {
        return new String(clientId);
    }
}

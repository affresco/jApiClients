package deribit.client.core;

import deribit.client.DeribitCredentials;
import deribit.client.delegates.SessionDelegate;
import clients.events.ClientCreationFailureEventClient;
import clients.events.ClientResetFailureEvent;
import clients.events.ConnectionLossEvent;
import utilities.Identity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DeribitClientMonitor {


    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    // Keeps the client connected at all costs
    public boolean keepConnected = true;

    // Client can be reset
    protected String clientId;
    protected DeribitWebsocketClient client;

    // Delegates for making sessions request
    public SessionDelegate session;

    // Enable cancel on disconnect when client is initialized
    private boolean cancelOnDisconnect = true;

    // Enable heartbeat when client is initialized
    private boolean heartbeat = false;

    // Credentials and url (final)
    private final DeribitCredentials credentials;
    private final String url;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public DeribitClientMonitor(String url, DeribitCredentials credentials) {
        this(url, credentials, true, true);
    }

    public DeribitClientMonitor(String url, DeribitCredentials credentials, boolean cancelOnDisconnect, boolean heartbeat) {

        // Credentials
        this.credentials = credentials;

        // Corresponding url (as string)
        this.url = url;

        // Cancel orders on disconnect
        this.cancelOnDisconnect = cancelOnDisconnect;

        // Set heartbeat
        this.heartbeat = heartbeat;

        // Setup this object
        setup();
    }

    // ##################################################################
    // CORE METHODS
    // ##################################################################

    private void setup() {

        // Connect to relevant events
        EventBus.getDefault().register(this);

        // Connect on init
        resetClient();

    }

    // ##################################################################
    // EVENTS
    // ##################################################################

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onConnectionLossEvent(ConnectionLossEvent event) {
        if (keepConnected) {
            resetClient();
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onClientCreationFailureEvent(ClientCreationFailureEventClient event) {
        if (keepConnected) {
            resetClient();
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onClientResetFailureEvent(ClientResetFailureEvent event) {
        // pass
    }

    // ##################################################################
    // CORE METHODS: CLIENT CREATION
    // ##################################################################

    private void createClient() {

        // Try to create the client
        try {
            if (client == null) {
                this.clientId = Identity.generateId(8);
                client = new DeribitWebsocketClient(url, credentials, clientId);
                client.maybe_reconnect();
                return;
            }

            // Something went wrong.
        } catch (Exception e) {
            System.out.println("Failed attempt to create client.");
            e.printStackTrace();
        }

        // Notify
        EventBus.getDefault().post(new ClientCreationFailureEventClient(clientId));
    }

    // ##################################################################
    // CORE METHODS: CLIENT RESET
    // ##################################################################

    public void resetClient() {
        resetClient(5);
    }

    public void resetClient(int maxRetry) {

        // At least we need a client instance
        if (client == null) {
            createClient();
        }

        // Counter
        int retry = 0;
        int delay = 1000;

        do {
            try {
                // Check if connected
                if (client.isConnected()) {

                    // This will setup the
                    // client as requested by the user
                    performInitialSetup();

                    // We're done
                    return;
                }
                Thread.sleep(200);

            } catch (Exception e) {

                int sec = delay / 1000;
                System.out.println("Failed attempt to reset client. Retrying in " + sec + " seconds.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            retry++;

        } while (retry < maxRetry);

        // Send the event to notify connection loss
        System.out.println("All attempts to reset client have failed.");

        // Notify
        EventBus.getDefault().post(new ClientCreationFailureEventClient(clientId));
    }

    // ##################################################################
    // CORE METHODS: INITIAL SETUP OF CLIENT FEATURES
    // ##################################################################

    private void performInitialSetup() {

        if (cancelOnDisconnect) {
            enableCancelOnDisconnect();
        }

        if (heartbeat) {
            enableHeartbeat();
        }
    }

    private void enableCancelOnDisconnect() {

        if (session == null) {
            this.session = new SessionDelegate(this.client);
        }

        try {
            this.session.enableCancelOnDisconnect();
        } catch (Exception e) {
            System.out.println("Failed to enable cancel on disconnect.");
        }

    }

    private void enableHeartbeat() {

        if (session == null) {
            this.session = new SessionDelegate(this.client);
        }

        try {
            this.session.enableHeartbeat();
        } catch (Exception e) {
            System.out.println("Failed to enable heartbeat.");
        }
    }


}

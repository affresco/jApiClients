package jClient.base;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import org.json.JSONObject;


public abstract class BaseClient extends WebSocketClient  {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    protected Credentials credentials;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public BaseClient(URI serverURI, Credentials credentials) {
        super(serverURI);
        this.credentials = credentials;
    }

    public BaseClient(String url, Credentials credentials) throws URISyntaxException {
        super(new URI(url));
        this.credentials = credentials;
    }

    // ##################################################################
    // INTERFACE FOR SUB-CLASSES
    // ##################################################################

    public abstract String getOnOpenMessage();


    // ##################################################################
    // IMPLEMENTING INTERFACE OF SUPER CLASS
    // ##################################################################

    @Override
    public void onOpen(ServerHandshake handshakedata) {

        // Request sub-class for login message
        System.out.println("Retrieving connection message.");
        String content = getOnOpenMessage();

        System.out.println(content);

        // Serialize login json
        send(content);

        System.out.println("Connection opened.");

    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The code codes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    // ##################################################################
    // PUBLIC METHODS
    // ##################################################################

    public void send(JSONObject jsonObject) {
        super.send(jsonObject.toString());
    }



}


package deribit.client.core;

import deribit.client.DeribitCredentials;

import java.net.URISyntaxException;

public class DeribitSubscriptionClient extends DeribitWebsocketClient {
    public DeribitSubscriptionClient(String url, DeribitCredentials credentials, String clientId) throws URISyntaxException {
        super(url, credentials, clientId);
    }

    public DeribitSubscriptionClient(String url, DeribitCredentials credentials, String clientId, boolean blockOnConnection) throws URISyntaxException {
        super(url, credentials, clientId, blockOnConnection);
    }

    @Override
    public void onMessage(String message) {
        routeMessage(message);
    }

    private void routeMessage(String message){
        System.out.println(message);
    }



}

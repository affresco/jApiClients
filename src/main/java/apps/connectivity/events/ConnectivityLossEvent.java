package apps.connectivity.events;

import apps.connectivity.ApiServiceProvider;

import java.time.Instant;

public class ConnectivityLossEvent {

    private Instant timestamp;
    private ApiServiceProvider apiServiceProvider;

    public ConnectivityLossEvent(ApiServiceProvider apiServiceProvider){

    }

}

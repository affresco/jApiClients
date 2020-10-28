package deribit.client.endpoints;

public enum SessionEndpoints
{
    ENABLE_HEARTBEAT("public/set_heartbeat"),
    DISABLE_HEARTBEAT("public/disable_heartbeat"),

    GET_CANCEL_ON_DISCONNECT("private/get_cancel_on_disconnect"),
    ENABLE_CANCEL_ON_DISCONNECT("private/enable_cancel_on_disconnect"),
    DISABLE_CANCEL_ON_DISCONNECT("private/disable_cancel_on_disconnect");

    private final String endpoint;

    SessionEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}

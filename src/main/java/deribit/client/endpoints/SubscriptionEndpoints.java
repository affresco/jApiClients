package deribit.client.endpoints;

public enum SubscriptionEndpoints
{
    PUBLIC_SUBSCRIBE("public/subscribe"),
    PUBLIC_UNSUBSCRIBE("public/unsubscribe"),

    PRIVATE_SUBSCRIBE("private/subscribe"),
    PRIVATE_UNSUBSCRIBE("private/unsubscribe");

    private final String endpoint;

    SubscriptionEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}

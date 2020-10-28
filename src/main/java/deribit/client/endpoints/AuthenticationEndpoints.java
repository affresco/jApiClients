package deribit.client.endpoints;

public enum AuthenticationEndpoints
{
    LOGIN("public/auth"),
    LOGOUT("public/logout"),

    EXCHANGE_TOKEN("public/exchange_token"),
    FORK_TOKEN("public/fork_token");


    private final String endpoint;

    AuthenticationEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}

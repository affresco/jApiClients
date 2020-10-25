package jClient.delta.endpoints;

public enum SupportingEndpoints
{

    TEST("public/test"),
    SERVER_TIME("public/get_time");

    private final String endpoint;

    SupportingEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}

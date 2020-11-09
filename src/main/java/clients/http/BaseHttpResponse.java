package clients.http;

public class BaseHttpResponse {

    private final int statusCode;
    private final String body;

    public BaseHttpResponse(int statusCode, String body){
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return new String(body);
    }

    @Override
    public String toString() {
        return "BaseHttpResponse ('" + statusCode + '\'' + ')';
    }
}

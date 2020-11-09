package clients.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Objects;

public class BaseHttpClient {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private static final long DEFAULT_TIMEOUT = 5000;
    private long timeout;


    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public BaseHttpClient(){
        this(DEFAULT_TIMEOUT);
    }


    public BaseHttpClient(long timeout){
        this.timeout = Math.max(0, timeout);
    }

    // ##################################################################
    // CORE METHODS: SYNC GET
    // ##################################################################

    public BaseHttpResponse makeSyncGetRequest(URI uri) {

        if (Objects.isNull(uri)) {
            throw new IllegalArgumentException("Empty URI: unable to make request.");
        }

        try {

            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .timeout(Duration.ofMillis(timeout))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new BaseHttpResponse(response.statusCode(), response.body());
        }

        catch (Exception e) {
            return null;
        }
    }

    public BaseHttpResponse makeSyncGetRequest(String url) {

        if (Objects.isNull(url)) {
            return null;
        }
        return makeSyncGetRequest(URI.create(url));

    }
    // ##################################################################
    // GETTERS & SETTERS
    // ##################################################################

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }




}

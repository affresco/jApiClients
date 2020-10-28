package deribit.client;

import clients.models.credentials.Credentials;
import org.json.JSONObject;

import java.util.HashMap;

public class DeribitCredentials extends Credentials {

    public DeribitCredentials(String key, String secret, String url) {
        super(key, secret, url);
    }

    @Override
    public JSONObject asJson() {
        HashMap<String, String> raw = super.asHashMap();
        JSONObject output = new JSONObject();
        output.put("client_id", raw.get("key"));
        output.put("client_secret", raw.get("secret"));
        return output;
    }

    @Override
    public HashMap<String, String> asHashMap() {
        HashMap<String, String> raw = super.asHashMap();
        HashMap<String, String> output = new HashMap<>();
        output.put("client_id", raw.get("key"));
        output.put("client_secret", raw.get("secret"));
        return output;
    }
}

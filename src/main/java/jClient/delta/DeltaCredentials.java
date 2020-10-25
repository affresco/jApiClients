package jClient.delta;

import jClient.base.Credentials;
import org.json.JSONObject;

import java.util.HashMap;

public class DeltaCredentials extends Credentials {

    public DeltaCredentials(String key, String secret, String url) {
        super(key, secret, url);
    }

    @Override
    public JSONObject as_json() {
        HashMap<String, String> raw = super.as_hash_map();
        JSONObject output = new JSONObject();
        output.put("client_id", raw.get("key"));
        output.put("client_secret", raw.get("secret"));
        return output;
    }

    @Override
    public HashMap<String, String> as_hash_map() {
        HashMap<String, String> raw = super.as_hash_map();
        HashMap<String, String> output = new HashMap<>();
        output.put("client_id", raw.get("key"));
        output.put("client_secret", raw.get("secret"));
        return output;
    }
}

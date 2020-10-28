package clients.models.credentials;

import org.json.JSONObject;

import java.util.HashMap;

public class Credentials {

    // Context
    private final String url;

    // Core
    private final String key;
    private final String secret;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public Credentials(String key, String secret, String url) {
        this.url = url;
        this.key = key;
        this.secret = secret;
    }

    // ##################################################################
    // POSITION RISK EXPOSURE FACTORY
    // ##################################################################

    public String getUrl() {
        return url;
    }

    public String getKey() {
        return key;
    }

    public String getSecret() {
        return secret;
    }

    // ##################################################################
    // POSITION RISK EXPOSURE FACTORY
    // ##################################################################

    public JSONObject asJson(){
        org.json.JSONObject json = new org.json.JSONObject();
        json.put("key", this.key);
        json.put("secret", this.secret);
        return json;
    }

    public HashMap<String, String> asHashMap(){
        HashMap<String, String> res = new HashMap<>();
        res.put("key", getKey());
        res.put("secret", getSecret());
        return res;
    }

}

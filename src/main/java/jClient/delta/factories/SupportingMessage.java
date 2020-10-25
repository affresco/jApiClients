package jClient.delta.factories;

import jClient.base.Message;
import jClient.delta.DeltaMessageFactory;
import jClient.delta.endpoints.SupportingEndpoints;
import org.json.JSONObject;

public class SupportingMessage {

    public static Message testWithoutException() {
        /*
         * Build a JSON message for making a 'valid' test request.
         *
         * */
        String method = SupportingEndpoints.TEST.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("", "");
        return DeltaMessageFactory.buildMessage(method, params);
    }

}

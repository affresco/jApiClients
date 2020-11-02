package deribit.client.factories;

import clients.models.messages.Message;
import deribit.client.endpoints.SupportingEndpoints;
import org.json.JSONObject;

public class SupportingMessage extends MessageFactory  {

    public static Message testWithoutException() {
        /*
         * Build a JSON message for making a 'valid' test request.
         *
         * */
        String method = SupportingEndpoints.TEST.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("", "");
        return buildMessage(method, params);
    }

}

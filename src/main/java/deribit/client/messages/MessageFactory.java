package deribit.client.messages;

import clients.models.messages.Message;
import utilities.Identity;

import org.json.JSONObject;


public class MessageFactory {

    public static Message buildMessage(String endpoint, JSONObject params) {
        String id = Identity.generateId();
        return buildMessage(endpoint, params, id);
    }

    public static Message buildMessage(String endpoint, JSONObject params, String id) {

        // Basic wrapper for Delta exchange
        JSONObject content = new JSONObject();

        // From the docs
        content.put("jsonrpc", "2.0");
        content.put("id", id);

        // Endpoint-like string for websocket
        content.put("method", endpoint);

        // Add params (content) to wrapper
        content.put("params", params);

        // Return
        return new Message(content, id);
    }

}

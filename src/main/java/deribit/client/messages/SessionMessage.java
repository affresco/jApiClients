package deribit.client.messages;

import clients.models.messages.Message;
import deribit.client.endpoints.SessionEndpoints;
import org.json.JSONObject;

public class SessionMessage extends MessageFactory  {

    // ##################################################################
    // CANCEL ON DISCONNECT
    // ##################################################################

    public static Message enableCancelOnDisconnect() {
        String method = SessionEndpoints.ENABLE_CANCEL_ON_DISCONNECT.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("scope", "connection");
        return buildMessage(method, params);
    }

    public static Message disableCancelOnDisconnect() {
        String method = SessionEndpoints.DISABLE_CANCEL_ON_DISCONNECT.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("scope", "connection");
        return buildMessage(method, params);
    }

    public static Message getCancelOnDisconnect() {
        String method = SessionEndpoints.GET_CANCEL_ON_DISCONNECT.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("scope", "connection");
        return buildMessage(method, params);
    }

    // ##################################################################
    // HEARTBEAT
    // ##################################################################

    public static Message enableHeartbeat() {
        return enableHeartbeat(10);
    }

    public static Message enableHeartbeat(int interval) {
        String method = SessionEndpoints.ENABLE_HEARTBEAT.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("interval", interval);
        return buildMessage(method, params);
    }

    public static Message disableHeartbeat() {
        String method = SessionEndpoints.DISABLE_HEARTBEAT.getEndpoint();
        JSONObject params = new JSONObject();
        return buildMessage(method, params);
    }


}

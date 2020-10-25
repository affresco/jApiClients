package jClient.delta.factories;

import jClient.base.Message;
import jClient.delta.DeltaMessageFactory;
import jClient.delta.endpoints.SessionEndpoints;
import org.json.JSONObject;

public class SessionMessage {

    // ##################################################################
    // CANCEL ON DISCONNECT
    // ##################################################################

    public static Message enableCancelOnDisconnect() {
        String method = SessionEndpoints.ENABLE_CANCEL_ON_DISCONNECT.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("scope", "connection");
        return DeltaMessageFactory.buildMessage(method, params);
    }

    public static Message disableCancelOnDisconnect() {
        String method = SessionEndpoints.DISABLE_CANCEL_ON_DISCONNECT.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("scope", "connection");
        return DeltaMessageFactory.buildMessage(method, params);
    }

    public static Message getCancelOnDisconnect() {
        String method = SessionEndpoints.GET_CANCEL_ON_DISCONNECT.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("scope", "connection");
        return DeltaMessageFactory.buildMessage(method, params);
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
        return DeltaMessageFactory.buildMessage(method, params);
    }

    public static Message disableHeartbeat() {
        String method = SessionEndpoints.DISABLE_HEARTBEAT.getEndpoint();
        JSONObject params = new JSONObject();
        return DeltaMessageFactory.buildMessage(method, params);
    }


}

package jClient.delta.factories;

import jClient.base.Message;
import jClient.delta.endpoints.AccountEndpoints;
import jClient.delta.DeltaMessageFactory;
import org.json.JSONObject;

public class AccountMessage {

    public static Message getPosition(String instrument) {
        String method = AccountEndpoints.GET_POSITION.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("instrument_name", instrument.toUpperCase());
        return DeltaMessageFactory.buildMessage(method, params);

    }



}

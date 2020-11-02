package deribit.client.factories;

import clients.models.messages.Message;
import deribit.client.endpoints.AccountEndpoints;
import org.json.JSONObject;

public class AccountMessage extends MessageFactory {

    public static Message getPosition(String instrument) {
        String method = AccountEndpoints.GET_POSITION.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("instrument_name", instrument.toUpperCase());
        return buildMessage(method, params);

    }



}

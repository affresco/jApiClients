package deribit.client.messages;

import clients.models.messages.Message;
import deribit.client.endpoints.MarketEndpoints;
import org.json.JSONObject;

public class MarketMessage extends MessageFactory  {

    public static Message getInstruments(String currency) {
        String method = MarketEndpoints.GET_INSTRUMENTS.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("currency", currency.toUpperCase());
        return buildMessage(method, params);
    }



}
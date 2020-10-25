package jClient.delta.factories;

import jClient.base.Message;
import jClient.delta.DeltaMessageFactory;
import jClient.delta.endpoints.MarketEndpoints;
import org.json.JSONObject;

public class MarketMessage {

    public static Message getInstruments(String currency) {
        /*
         * Build a JSON message for retrieving a position on a given instrument.
         *
         * */
        String method = MarketEndpoints.GET_INSTRUMENTS.getEndpoint();
        JSONObject params = new JSONObject();
        params.put("currency", currency.toUpperCase());
        return DeltaMessageFactory.buildMessage(method, params);
    }



}
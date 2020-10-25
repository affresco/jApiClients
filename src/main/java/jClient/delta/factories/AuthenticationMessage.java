package jClient.delta.factories;
import jClient.base.Message;
import jClient.delta.DeltaMessageFactory;
import jClient.delta.endpoints.AuthenticationEndpoints;

import jClient.base.Credentials;

import org.json.JSONObject;

public class AuthenticationMessage {

    public static Message login(Credentials credentials){
        /*
        * Build a JSON message for login into the Delta API.
        *
        * */
        AuthenticationEndpoints auth = AuthenticationEndpoints.LOGIN;
        String auth_method = auth.getEndpoint();
        JSONObject params = credentials.as_json();
        params.put("grant_type", "client_credentials");
        return DeltaMessageFactory.buildMessage(auth_method, params);
    }

}

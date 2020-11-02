package deribit.client.factories;
import clients.models.messages.Message;
import deribit.client.endpoints.AuthenticationEndpoints;

import clients.models.credentials.Credentials;

import org.json.JSONObject;

public class AuthenticationMessage extends MessageFactory {

    public static Message login(Credentials credentials){
        AuthenticationEndpoints auth = AuthenticationEndpoints.LOGIN;
        String auth_method = auth.getEndpoint();
        JSONObject params = credentials.asJson();
        params.put("grant_type", "client_credentials");
        return buildMessage(auth_method, params);
    }

}

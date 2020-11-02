package deribit.client.models;

import clients.models.messages.Message;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class SubscriptionMessage extends Message {

    private final ArrayList<String> channels;

    public SubscriptionMessage(JSONObject content, String messageId, ArrayList<String> channels) {
        super(content, messageId);

        if (Objects.isNull(channels)){
            this.channels = new ArrayList<>();
        }
        else{
            this.channels = channels;
        }
    }

    public ArrayList<String> getChannels(){
        return new ArrayList<>(channels);
    }

}

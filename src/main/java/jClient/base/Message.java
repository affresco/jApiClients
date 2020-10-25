package jClient.base;

import org.json.JSONObject;

public class Message {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private final String messageId;
    private final String content;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public Message(JSONObject content, String messageId) {
         this.content = content.toString();
         this.messageId = messageId;
    }

    // ##################################################################
    // BASIC FUNCTIONALITIES
    // ##################################################################

    public String getMessageId() {
        return this.messageId;
    }

    public String getContent() {
        return this.content;
    }

}

package deribit.client.handlers;

import clients.models.messages.MessageHandler;

public class TestMessageHandler implements MessageHandler {

    @Override
    public String handleMessage(String message) {
        return message;
    }
}

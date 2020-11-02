package apps.market.models.quotes;

import apps.market.exceptions.InvalidMessageException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeribitQuoteFactory {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private DeribitQuoteFactory(){}

    public static DeribitQuote fromSubscriptionMessage(String message) throws InvalidMessageException {

        try {
            DeribitQuoteMessage m = objectMapper.readValue(message, DeribitQuoteMessage.class);
            return m.getQuote();
        }

        catch (JsonProcessingException e) {
            throw new InvalidMessageException();
        }
    }

}

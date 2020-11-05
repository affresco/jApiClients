package apps.market.models.quotes;

import apps.market.exceptions.InvalidMessageException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;

public class DeribitQuoteFactory {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final Instant timestamp;

    private DeribitQuoteFactory(){
        this.timestamp = Instant.now();
    }

    public static DeribitQuote fromQuoteSubscriptionMessage(String message) throws InvalidMessageException {

        try {
            DeribitQuoteMessage m = objectMapper.readValue(message, DeribitQuoteMessage.class);
            return m.getQuote();
        }

        catch (JsonProcessingException e) {
            throw new InvalidMessageException();
        }
    }

    public static DeribitQuote fromIndexSubscriptionMessage(String message) throws InvalidMessageException {

        try {
            DeribitQuoteMessage m = objectMapper.readValue(message, DeribitIndexQuoteMessage.class);
            return m.getQuote();
        }

        catch (JsonProcessingException e) {
            throw new InvalidMessageException();
        }
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public Instant getTimestamp() {
        return Instant.from(timestamp);
    }
}

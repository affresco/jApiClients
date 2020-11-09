package commons.models.expiries;

import java.time.Instant;

public interface Expiry {

    // Must be able to provide expiry
    // ...as a string
    String getExpiryDateAsString();
    // ...as an instant
    Instant getExpiry();

}

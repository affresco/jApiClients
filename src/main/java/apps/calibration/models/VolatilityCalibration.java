package apps.calibration.models;

import commons.models.data.SimpleQuote;
import commons.models.data.SortedDataStructure;
import commons.models.data.VolatilitySmile;

import java.time.Instant;

public class VolatilityCalibration extends VolatilitySmile {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private final String id;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public VolatilityCalibration(SimpleQuote referenceSpot, SimpleQuote referenceForward, SortedDataStructure bid, SortedDataStructure ask, Instant expiry) {
        this(referenceSpot, referenceForward, bid, ask, expiry, Instant.now());
    }

    public VolatilityCalibration(SimpleQuote referenceSpot, SimpleQuote referenceForward, SortedDataStructure bid, SortedDataStructure ask, Instant expiry, Instant filtration) {
        super(referenceSpot, referenceForward, bid, ask, expiry, filtration);
        this.id = utilities.Identity.generateId();
    }

    public VolatilityCalibration(VolatilitySmile ts) {
        super(ts);
        this.id = utilities.Identity.generateId();
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public String getId() {
        return new String(id);
    }


}

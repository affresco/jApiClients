package commons.models.data;

import java.time.Instant;

public class VolatilitySmile {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    protected final SimpleQuote referenceSpot;
    protected final SimpleQuote referenceForward;

    protected final SortedDataStructure bid;
    protected final SortedDataStructure ask;

    protected final Instant filtration;
    protected final Instant expiry;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public VolatilitySmile(SimpleQuote referenceSpot, SimpleQuote referenceForward, SortedDataStructure bid, SortedDataStructure ask, Instant expiry) {
        this(referenceSpot, referenceForward, bid, ask, expiry, Instant.now());
    }

    public VolatilitySmile(SimpleQuote referenceSpot, SimpleQuote referenceForward, SortedDataStructure bid, SortedDataStructure ask, Instant expiry, Instant filtration) {
        this.referenceSpot = referenceSpot;
        this.referenceForward = referenceForward;
        this.bid = bid;
        this.ask = ask;
        this.expiry = expiry;
        this.filtration = filtration;
    }

    public VolatilitySmile(VolatilitySmile ts) {
        this.referenceSpot = ts.getReferenceSpot();
        this.referenceForward = ts.getReferenceForward();
        this.bid = ts.getBid();
        this.ask = ts.getAsk();
        this.filtration = ts.getFiltration();
        this.expiry = ts.getExpiry();
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public SortedDataStructure getBid() {
        return new SortedDataStructure(bid);
    }

    public SortedDataStructure getAsk() {
        return new SortedDataStructure(ask);
    }

    public Instant getFiltration() {
        return Instant.from(filtration);
    }

    public SimpleQuote getReferenceSpot() {
        return new SimpleQuote(referenceSpot);
    }

    public SimpleQuote getReferenceForward() {
        return new SimpleQuote(referenceForward);
    }

    public Instant getExpiry() {
        return Instant.from(expiry);
    }
}

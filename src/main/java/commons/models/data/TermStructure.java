package commons.models.data;

import java.time.Instant;

public class TermStructure {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    protected final SimpleQuote referencePrice;

    protected final TermStructure bid;
    protected final TermStructure ask;

    protected final Instant filtration;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public TermStructure(SimpleQuote referencePrice, TermStructure bid, TermStructure ask) {
        this(referencePrice, bid, ask, Instant.now());
    }

    public TermStructure(SimpleQuote referencePrice, TermStructure bid, TermStructure ask, Instant filtration) {
        this.referencePrice = referencePrice;
        this.bid = bid;
        this.ask = ask;
        this.filtration = filtration;
    }

    public TermStructure(TermStructure ts) {
        this.referencePrice = ts.getReferencePrice();
        this.bid = ts.getBid();
        this.ask = ts.getAsk();
        this.filtration = ts.getFiltration();
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public TermStructure getBid() {
        return new TermStructure(bid);
    }

    public TermStructure getAsk() {
        return new TermStructure(ask);
    }

    public Instant getFiltration() {
        return Instant.from(filtration);
    }

    public SimpleQuote getReferencePrice() {
        return new SimpleQuote(referencePrice);
    }

}

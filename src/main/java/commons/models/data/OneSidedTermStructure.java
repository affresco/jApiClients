package commons.models.data;

import java.time.Instant;

public class OneSidedTermStructure extends SortedDataStructure {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    protected final Instant filtration;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public OneSidedTermStructure(SimpleQuote referencePrice, double[] xValues, double[] yValues, Instant filtration) {
        super(referencePrice, xValues, yValues);
        this.filtration = filtration;
    }

    public OneSidedTermStructure(SimpleQuote referencePrice, double[] xValues, double[] yValues) {
        this(referencePrice, xValues, yValues, Instant.now());
    }

    public OneSidedTermStructure(OneSidedTermStructure s) {
        super(s);
        this.filtration = s.getFiltration();
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public Instant getFiltration() {
        return Instant.from(filtration);
    }

}

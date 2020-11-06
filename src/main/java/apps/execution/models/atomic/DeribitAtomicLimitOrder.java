package apps.execution.models.atomic;

import commons.models.instruments.BaseInstrument;
import commons.standards.OrderType;
import commons.standards.QuoteDirection;
import commons.standards.TimeInForce;
import utilities.Identity;

public class DeribitAtomicLimitOrder extends DeribitAtomicOrder{

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private final double limitPrice;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public DeribitAtomicLimitOrder(BaseInstrument instrument, double amount, QuoteDirection direction, double limitPrice, TimeInForce timeInForce, String label) {
        super(instrument, amount, direction, OrderType.MARKET, timeInForce, label);
        this.limitPrice = limitPrice;
    }

    public DeribitAtomicLimitOrder(BaseInstrument instrument, double amount, QuoteDirection direction, double limitPrice, String label) {
        this(instrument, amount, direction, limitPrice, TimeInForce.GOOD_TIL_CANCELLED, label);
    }

    public DeribitAtomicLimitOrder(BaseInstrument instrument, double amount, QuoteDirection direction, double limitPrice) {
        this(instrument, amount, direction, limitPrice, TimeInForce.GOOD_TIL_CANCELLED, Identity.generateId(8).toUpperCase());
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public double getLimitPrice() {
        return limitPrice;
    }
}

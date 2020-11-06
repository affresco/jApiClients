package apps.execution.models.atomic;

import commons.models.instruments.BaseInstrument;
import commons.standards.OrderType;
import commons.standards.QuoteDirection;
import commons.standards.TimeInForce;
import utilities.Identity;

public class DeribitAtomicStopLimitOrder extends DeribitAtomicOrder{

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private final double limitPrice;
    private final double stopPrice;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public DeribitAtomicStopLimitOrder(BaseInstrument instrument, double amount, QuoteDirection direction, double limitPrice, double stopPrice, TimeInForce timeInForce, String label) {
        super(instrument, amount, direction, OrderType.MARKET, timeInForce, label);
        this.limitPrice = limitPrice;
        this.stopPrice = stopPrice;
    }

    public DeribitAtomicStopLimitOrder(BaseInstrument instrument, double amount, QuoteDirection direction, double limitPrice, double stopPrice, String label) {
        this(instrument, amount, direction, limitPrice, stopPrice, TimeInForce.GOOD_TIL_CANCELLED, label);
    }

    public DeribitAtomicStopLimitOrder(BaseInstrument instrument, double amount, QuoteDirection direction, double limitPrice, double stopPrice) {
        this(instrument, amount, direction, limitPrice, stopPrice, TimeInForce.GOOD_TIL_CANCELLED, Identity.generateId(8).toUpperCase());
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public double getLimitPrice() {
        return limitPrice;
    }

    public double getStopPrice() {
        return stopPrice;
    }
}

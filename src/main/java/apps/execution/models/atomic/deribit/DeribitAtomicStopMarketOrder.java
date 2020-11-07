package apps.execution.models.atomic.deribit;

import commons.models.instruments.BaseInstrument;
import commons.standards.OrderType;
import commons.standards.QuoteDirection;
import commons.standards.TimeInForce;
import utilities.Identity;

public class DeribitAtomicStopMarketOrder extends DeribitAtomicOrder{

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private final double stopPrice;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public DeribitAtomicStopMarketOrder(BaseInstrument instrument, double amount, QuoteDirection direction, double stopPrice, TimeInForce timeInForce, String label) {
        super(instrument, amount, direction, OrderType.MARKET, timeInForce, label);
        this.stopPrice = stopPrice;
    }

    public DeribitAtomicStopMarketOrder(BaseInstrument instrument, double amount, QuoteDirection direction, double stopPrice, String label) {
        this(instrument, amount, direction, stopPrice, TimeInForce.GOOD_TIL_CANCELLED, label);
    }

    public DeribitAtomicStopMarketOrder(BaseInstrument instrument, double amount, QuoteDirection direction, double stopPrice) {
        this(instrument, amount, direction, stopPrice, TimeInForce.GOOD_TIL_CANCELLED, Identity.generateId(8).toUpperCase());
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public double getStopPrice() {
        return stopPrice;
    }

}

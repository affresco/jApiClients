package apps.execution.models.atomic.deribit;

import commons.models.instruments.BaseInstrument;
import commons.standards.OrderType;
import commons.standards.QuoteDirection;
import commons.standards.TimeInForce;

public class DeribitAtomicMarketOrder extends DeribitAtomicOrder{

    public DeribitAtomicMarketOrder(BaseInstrument instrument, double amount, QuoteDirection direction, TimeInForce timeInForce, String label) {
        super(instrument, amount, direction, OrderType.MARKET, timeInForce, label);
    }

    public DeribitAtomicMarketOrder(BaseInstrument instrument, double amount, QuoteDirection direction, String label) {
        this(instrument, amount, direction, TimeInForce.GOOD_TIL_CANCELLED, label);
    }

    public DeribitAtomicMarketOrder(BaseInstrument instrument, double amount, QuoteDirection direction) {
        this(instrument, amount, direction, TimeInForce.GOOD_TIL_CANCELLED, utilities.Identity.generateId(8).toUpperCase());
    }


}

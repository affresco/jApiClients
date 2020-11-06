package apps.execution.models.orders;

import apps.execution.models.atomic.DeribitAtomicMarketOrder;
import apps.execution.models.support.DeribitAtomicOrderSchedule;
import commons.standards.QuoteDirection;
import commons.standards.TimeInForce;
import deribit.models.instruments.DeribitInstrument;

import java.util.HashMap;

public class DeribitMarketOrder extends DeribitOrder {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private final DeribitAtomicMarketOrder order;

    // ##################################################################
    // CONSTRUCTOR
    // ##################################################################

    public DeribitMarketOrder(DeribitInstrument instrument, double amount, QuoteDirection direction, TimeInForce timeInForce, String label) {
        super(instrument, amount, direction, timeInForce, label);
        this.order = new DeribitAtomicMarketOrder(instrument, amount, direction, timeInForce, label);
    }

    // ##################################################################
    // IMPLEMENTATIONS
    // ##################################################################

    @Override
    public DeribitAtomicOrderSchedule getExecutionSchedule() {
        DeribitAtomicOrderSchedule result = new DeribitAtomicOrderSchedule();
        result.add(0, order);
        return result;
    }
}

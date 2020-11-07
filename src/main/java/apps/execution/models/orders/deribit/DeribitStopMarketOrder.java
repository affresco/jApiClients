package apps.execution.models.orders.deribit;

import apps.execution.models.atomic.deribit.DeribitAtomicStopMarketOrder;
import apps.execution.models.support.AtomicOrderSchedule;
import apps.execution.models.support.DeribitAtomicOrderSchedule;
import commons.standards.QuoteDirection;
import commons.standards.TimeInForce;
import deribit.models.instruments.DeribitInstrument;

public class DeribitStopMarketOrder extends DeribitOrder {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private final DeribitAtomicStopMarketOrder order;

    // ##################################################################
    // CONSTRUCTOR
    // ##################################################################

    public DeribitStopMarketOrder(DeribitInstrument instrument, double amount, QuoteDirection direction, double stopPrice, TimeInForce timeInForce, String label) {
        super(instrument, amount, direction, timeInForce, label);
        this.order = new DeribitAtomicStopMarketOrder(instrument, amount, direction, stopPrice, timeInForce, label);
    }

    // ##################################################################
    // IMPLEMENTATIONS
    // ##################################################################

    @Override
    public AtomicOrderSchedule getExecutionSchedule() {
        DeribitAtomicOrderSchedule result = new DeribitAtomicOrderSchedule();
        result.add(0, order);
        return result;
    }
}

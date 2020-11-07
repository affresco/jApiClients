package apps.execution.models.orders.deribit;

import apps.execution.models.atomic.deribit.DeribitAtomicLimitOrder;
import apps.execution.models.support.AtomicOrderSchedule;
import apps.execution.models.support.DeribitAtomicOrderSchedule;
import commons.standards.QuoteDirection;
import commons.standards.TimeInForce;
import deribit.models.instruments.DeribitInstrument;

public class DeribitLimitOrder extends DeribitOrder {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private final DeribitAtomicLimitOrder order;

    // ##################################################################
    // CONSTRUCTOR
    // ##################################################################

    public DeribitLimitOrder(DeribitInstrument instrument, double amount, QuoteDirection direction, double limitPrice, TimeInForce timeInForce, String label) {
        super(instrument, amount, direction, timeInForce, label);
        this.order = new DeribitAtomicLimitOrder(instrument, amount, direction, limitPrice, timeInForce, label);
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

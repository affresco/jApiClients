package apps.execution.models.orders;

import apps.execution.models.atomic.DeribitAtomicLimitOrder;
import apps.execution.models.atomic.DeribitAtomicOrder;
import apps.execution.models.atomic.DeribitAtomicStopLimitOrder;
import apps.execution.models.support.DeribitAtomicOrderSchedule;
import commons.models.instruments.BaseInstrument;
import commons.standards.QuoteDirection;
import commons.standards.TimeInForce;
import deribit.models.instruments.DeribitInstrument;

import java.util.HashMap;

public class DeribitStopLimitOrder extends DeribitOrder {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private final DeribitAtomicStopLimitOrder order;

    // ##################################################################
    // CONSTRUCTOR
    // ##################################################################

    public DeribitStopLimitOrder(DeribitInstrument instrument, double amount, QuoteDirection direction, double limitPrice, double stopPrice, TimeInForce timeInForce, String label) {
        super(instrument, amount, direction, timeInForce, label);
        this.order = new DeribitAtomicStopLimitOrder(instrument, amount, direction, limitPrice, stopPrice, timeInForce, label);
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

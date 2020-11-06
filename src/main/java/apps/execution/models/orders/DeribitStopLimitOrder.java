package apps.execution.models.orders;

import apps.execution.models.atomic.DeribitAtomicLimitOrder;
import apps.execution.models.atomic.DeribitAtomicOrder;
import apps.execution.models.atomic.DeribitAtomicStopLimitOrder;
import commons.models.instruments.BaseInstrument;
import commons.standards.QuoteDirection;
import commons.standards.TimeInForce;

import java.util.HashMap;

public class DeribitStopLimitOrder extends DeribitOrder {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private final DeribitAtomicStopLimitOrder order;

    // ##################################################################
    // CONSTRUCTOR
    // ##################################################################

    public DeribitStopLimitOrder(BaseInstrument instrument, double amount, QuoteDirection direction, double limitPrice, double stopPrice, TimeInForce timeInForce, String label) {
        super(instrument, amount, direction, timeInForce, label);
        this.order = new DeribitAtomicStopLimitOrder(instrument, amount, direction, limitPrice, stopPrice, timeInForce, label);
    }

    // ##################################################################
    // IMPLEMENTATIONS
    // ##################################################################

    @Override
    public HashMap<Integer, DeribitAtomicOrder> getExecutionSchedule() {
        HashMap<Integer, DeribitAtomicOrder> result = new HashMap<>();
        result.put(0, order);
        return result;
    }
}

package apps.execution.models.orders;

import apps.execution.models.atomic.DeribitAtomicMarketOrder;
import apps.execution.models.atomic.DeribitAtomicOrder;
import commons.models.instruments.BaseInstrument;
import commons.standards.QuoteDirection;
import commons.standards.TimeInForce;

import java.util.HashMap;

public class DeribitMarketOrder extends DeribitOrder {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private final DeribitAtomicMarketOrder order;

    // ##################################################################
    // CONSTRUCTOR
    // ##################################################################

    public DeribitMarketOrder(BaseInstrument instrument, double amount, QuoteDirection direction, TimeInForce timeInForce, String label) {
        super(instrument, amount, direction, timeInForce, label);
        this.order = new DeribitAtomicMarketOrder(instrument, amount, direction, timeInForce, label);
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

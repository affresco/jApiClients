package apps.execution.models.orders;

import apps.execution.models.atomic.DeribitAtomicLimitOrder;
import apps.execution.models.atomic.DeribitAtomicMarketOrder;
import apps.execution.models.atomic.DeribitAtomicOrder;
import apps.execution.models.support.DeribitAtomicOrderSchedule;
import commons.models.instruments.BaseInstrument;
import commons.standards.OrderType;
import commons.standards.QuoteDirection;
import commons.standards.TimeInForce;
import deribit.models.instruments.DeribitInstrument;

import java.sql.Time;
import java.util.HashMap;

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
    public DeribitAtomicOrderSchedule getExecutionSchedule() {
        DeribitAtomicOrderSchedule result = new DeribitAtomicOrderSchedule();
        result.add(0, order);
        return result;
    }
}

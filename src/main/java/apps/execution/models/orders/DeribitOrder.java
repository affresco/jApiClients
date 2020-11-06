package apps.execution.models.orders;

import apps.execution.models.atomic.DeribitAtomicOrder;
import commons.models.instruments.BaseInstrument;
import commons.standards.DerivativeExchange;
import commons.standards.OrderType;
import commons.standards.QuoteDirection;
import commons.standards.TimeInForce;

import java.time.Instant;
import java.util.HashMap;

public abstract class DeribitOrder {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    // These are by default
    private final DerivativeExchange exchange = DerivativeExchange.DERIBIT;
    private final Instant creationInstant;

    // From constructor
    private final BaseInstrument instrument;
    private final double amount;
    private final QuoteDirection direction;
    private final TimeInForce timeInForce;
    private final String label;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public DeribitOrder(BaseInstrument instrument, double amount, QuoteDirection direction, TimeInForce timeInForce, String label) {

        // Creation instant of this object
        this.creationInstant = Instant.now();

        // Fill-in attributes
        this.instrument = instrument;
        this.amount = amount;
        this.direction = direction;
        this.timeInForce = timeInForce;
        this.label = label;
    }

    // ##################################################################
    // ABSTRACT METHODS
    // ##################################################################

    public abstract HashMap<Integer, DeribitAtomicOrder> getExecutionSchedule();

}


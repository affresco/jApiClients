package apps.execution.models.orders.deribit;

import apps.execution.models.orders.Order;
import apps.execution.models.support.AtomicOrderSchedule;
import commons.standards.DerivativeExchange;
import commons.standards.QuoteDirection;
import commons.standards.TimeInForce;
import deribit.models.instruments.DeribitInstrument;

import java.time.Instant;

public abstract class DeribitOrder implements Order {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    // These are by default
    private final DerivativeExchange exchange = DerivativeExchange.DERIBIT;
    private final Instant creationInstant;

    // From constructor
    private final DeribitInstrument instrument;
    private final double amount;
    private final QuoteDirection direction;
    private final TimeInForce timeInForce;
    private final String label;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public DeribitOrder(DeribitInstrument instrument, double amount, QuoteDirection direction, TimeInForce timeInForce, String label) {

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

    public abstract AtomicOrderSchedule getExecutionSchedule();

    // ##################################################################
    // GETTERS
    // ##################################################################

    public double getAmount() {
        return amount;
    }

    public QuoteDirection getDirection() {
        return direction;
    }

    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    public String getLabel() {
        return label;
    }

    public Instant getCreationInstant() {
        return creationInstant;
    }

    public DerivativeExchange getExchange() {
        return exchange;
    }

    public DeribitInstrument getInstrument() {
        return instrument;
    }
}


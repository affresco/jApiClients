package apps.execution.models.atomic;

import commons.standards.DerivativeExchange;
import commons.models.instruments.BaseInstrument;
import commons.standards.OrderType;
import commons.standards.QuoteDirection;
import commons.standards.TimeInForce;

import java.time.Instant;

public class DeribitAtomicOrder {

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
    private final OrderType orderType;
    private final TimeInForce timeInForce;
    private final String label;


    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public DeribitAtomicOrder(BaseInstrument instrument, double amount, QuoteDirection direction, OrderType orderType, TimeInForce timeInForce, String label){

        // Creation instant of this object
        this.creationInstant = Instant.now();

        // Fill-in attributes
        this.instrument = instrument;
        this.amount = amount;
        this.direction = direction;
        this.orderType = orderType;
        this.timeInForce = timeInForce;
        this.label = label;
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public BaseInstrument getInstrument() {
        return instrument;
    }

    public double getAmount() {
        return amount;
    }

    public Instant getCreationInstant() {
        return creationInstant;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public QuoteDirection getDirection() {
        return direction;
    }

    public String getLabel() {
        return label;
    }

    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    public DerivativeExchange getExchange() {
        return exchange;
    }

    // ##################################################################
    // DISPLAY
    // ##################################################################

    @Override
    public String toString() {
        return "AtomicOrder{" +
                "instrument=" + instrument +
                ", amount=" + amount +
                ", direction=" + direction +
                ", orderType=" + orderType +
                ", label='" + label + '\'' +
                '}';
    }



}

package apps.calibration.events;

import commons.models.expiries.BaseExpiry;
import commons.models.expiries.Expiry;
import commons.standards.Cryptocurrency;
import commons.standards.DerivativeExchange;

public class VolatilitySmileCalibrationRequestEvent {

    private final DerivativeExchange exchange;
    private final Cryptocurrency currency;
    private final BaseExpiry expiry;

    public VolatilitySmileCalibrationRequestEvent(DerivativeExchange exchange, Cryptocurrency currency, BaseExpiry expiry) {
        this.exchange = exchange;
        this.currency = currency;
        this.expiry = expiry;
    }

    public DerivativeExchange getExchange() {
        return exchange;
    }

    public Cryptocurrency getCurrency() {
        // TODO: Needs some boxing up ??
        return currency;
    }

    public Expiry getExpiry() {
        // TODO: Needs some boxing up ??
        return expiry;
    }
}

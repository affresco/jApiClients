package commons.models.expiries;

import commons.standards.SettlementPeriod;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public abstract class BaseExpiry implements Expiry {

    private final Instant expiryDate;
    private final SettlementPeriod settlementPeriod;

    protected BaseExpiry(Builder<?> builder){
        this.expiryDate = builder.expiryDate;
        this.settlementPeriod = builder.settlementPeriod;
    }

    public BaseExpiry(BaseExpiry expiry){
        this.expiryDate = expiry.getExpiry();
        this.settlementPeriod = expiry.getSettlementPeriod();
    }

    public abstract static class Builder<T extends Builder<T>>{

        protected Instant expiryDate;
        protected SettlementPeriod settlementPeriod;

        public T setExpiryDate(Instant val){
            this.expiryDate = val;
            return self();
        }

        public T setSettlementPeriod(SettlementPeriod val){
            this.settlementPeriod = val;
            return self();
        }

        public abstract String getUniqueIdentifier();
        protected abstract BaseExpiry build();
        protected abstract T self();
    }

    public Instant getExpiry() {
        return Instant.from(expiryDate);
    }

    public String getExpiryDateAsString() {
        DateTimeFormatter formatter = DateTimeFormatter
                        .ofLocalizedDateTime( FormatStyle.SHORT )
                        .withZone(ZoneId.from(ZoneOffset.UTC) );
        return formatter.format( expiryDate );
    }

    public SettlementPeriod getSettlementPeriod() {
        return settlementPeriod;
    }
}

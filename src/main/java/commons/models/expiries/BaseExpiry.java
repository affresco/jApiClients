package commons.models.expiries;

import commons.standards.SettlementPeriod;

import java.util.Date;

public abstract class BaseExpiry {

    private final Date expiryDate;
    private final SettlementPeriod settlementPeriod;

    protected BaseExpiry(Builder<?> builder){
        this.expiryDate = builder.expiryDate;
        this.settlementPeriod = builder.settlementPeriod;
    }

    public abstract static class Builder<T extends Builder<T>>{

        protected Date expiryDate;
        protected SettlementPeriod settlementPeriod;

        public T setExpiryDate(Date val){
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

}

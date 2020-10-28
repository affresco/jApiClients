package commons.models.instruments;


import commons.models.currencies.BaseCurrency;

public abstract class BaseInstrument {

    private final BaseCurrency currency;
    private final String symbol;

    protected BaseInstrument(Builder<?> builder){
        this.symbol = builder.symbol;
        this.currency = builder.currency;
    }

    public abstract static class Builder<T extends Builder<T>>{

        protected String symbol;
        protected BaseCurrency currency;

        public T setSymbol(String val){
            this.symbol = val;
            return self();
        }

        public T setCurrency(BaseCurrency val){
            this.currency = val;
            return self();
        }

        protected abstract String getUniqueIdentifier();
        protected abstract BaseInstrument build();
        protected abstract T self();
    }

}

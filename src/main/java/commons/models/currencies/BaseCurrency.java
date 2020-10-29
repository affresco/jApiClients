package commons.models.currencies;

public abstract class BaseCurrency {

    protected final String quoteCurrency;
    protected final String baseCurrency;
    protected final String symbol;
    protected final String name;

    protected BaseCurrency(Builder<?> builder){
        this.quoteCurrency = builder.quoteCurrency;
        this.baseCurrency = builder.baseCurrency;
        this.symbol = builder.symbol;
        this.name = builder.name;
    }

    public abstract static class Builder<T extends Builder<T>>{

        protected String quoteCurrency;
        protected String baseCurrency;
        protected String symbol;
        protected String name;

        public T setQuoteCurrency(String val){
            this.quoteCurrency = val;
            return self();
        }

        public T setBaseCurrency(String val){
            this.baseCurrency = val;
            return self();
        }

        public T setSymbol(String val){
            this.symbol = val;
            return self();
        }

        public T setName(String val){
            this.name = val;
            return self();
        }

        public abstract String getUniqueIdentifier();
        protected abstract BaseCurrency build();
        protected abstract T self();
    }

}

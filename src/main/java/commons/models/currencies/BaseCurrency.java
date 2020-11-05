package commons.models.currencies;

import commons.standards.Cryptocurrency;
import commons.standards.FiatCurrency;

public abstract class BaseCurrency {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    protected final FiatCurrency quoteCurrency;
    protected final Cryptocurrency baseCurrency;
    protected final String symbol;
    protected final String name;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    protected BaseCurrency(Builder<?> builder){
        this.quoteCurrency = builder.quoteCurrency;
        this.baseCurrency = builder.baseCurrency;
        this.symbol = builder.symbol;
        this.name = builder.name;
    }

    protected BaseCurrency(BaseCurrency currency){
        this.quoteCurrency = currency.getQuoteCurrency();
        this.baseCurrency = currency.getBaseCurrency();
        this.symbol = currency.getSymbol();
        this.name = currency.getName();
    }

    // ##################################################################
    // BUILDER
    // ##################################################################

    public abstract static class Builder<T extends Builder<T>>{

        protected FiatCurrency quoteCurrency;
        protected Cryptocurrency baseCurrency;
        protected String symbol;
        protected String name;

        public T setQuoteCurrency(FiatCurrency val){
            this.quoteCurrency = val;
            return self();
        }

        public T setBaseCurrency(Cryptocurrency val){
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

    // ##################################################################
    // GETTERS
    // ##################################################################

    public Cryptocurrency getBaseCurrency() {
        return baseCurrency;
    }

    public String getName() {
        return new String(name);
    }

    public FiatCurrency getQuoteCurrency() {
        return quoteCurrency;
    }

    public String getSymbol() {
        return new String(symbol);
    }
}

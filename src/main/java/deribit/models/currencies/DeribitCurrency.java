package deribit.models.currencies;

import commons.models.currencies.BaseCurrency;
import commons.standards.Cryptocurrency;

import java.util.HashMap;

public class DeribitCurrency extends BaseCurrency {

    // This contains the instances present in the system
    private static final HashMap<String, DeribitCurrency> instances = new HashMap<>();

    protected final Integer feePrecision;
    protected final Integer minimumConfirmations;
    protected final Double minimumWithdrawalFee;
    protected final Double withdrawalFee;

    protected DeribitCurrency(Builder builder)
    {
        super(builder);
        this.feePrecision = builder.feePrecision;
        this.minimumConfirmations = builder.minimumConfirmations;
        this.minimumWithdrawalFee = builder.minimumWithdrawalFee;
        this.withdrawalFee = builder.withdrawalFee;
    }

    public DeribitCurrency(DeribitCurrency currency)
    {
        super(currency);
        this.feePrecision = currency.feePrecision;
        this.minimumConfirmations = currency.minimumConfirmations;
        this.minimumWithdrawalFee = currency.minimumWithdrawalFee;
        this.withdrawalFee = currency.withdrawalFee;
    }

    private static DeribitCurrency getInstance(DeribitCurrency.Builder builder){

        // Already present, just return
        String uniqueIdentifier = builder.getUniqueIdentifier();
        if (instances.containsKey(uniqueIdentifier)){
            return instances.get(uniqueIdentifier);
        }

        // Otherwise create it
        else {
            DeribitCurrency newCurrency = new DeribitCurrency(builder);
            synchronized (instances) {
                instances.put(builder.getUniqueIdentifier(), newCurrency);
            }
            return newCurrency;
        }
    }

    public static class Builder extends BaseCurrency.Builder<Builder>{

        protected Integer feePrecision;
        protected Integer minimumConfirmations;
        protected Double minimumWithdrawalFee;
        protected Double withdrawalFee;

        @Override
        public String getUniqueIdentifier() {
            return this.symbol;
        }

        @Override
        public DeribitCurrency build() {
            return DeribitCurrency.getInstance(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder setFeePrecision(Integer val) {
            this.feePrecision = val;
            return self();
        }

        public Builder setMinimumConfirmations(Integer val) {
            this.minimumConfirmations = val;
            return self();
        }

        public Builder setMinimumWithdrawalFee(Double val) {
            this.minimumWithdrawalFee = val;
            return self();
        }

        public Builder setWithdrawalFee(Double val) {
            this.withdrawalFee = val;
            return self();
        }
    }

    public Cryptocurrency getCryptoCurrency(){
        if (this.baseCurrency.equalsIgnoreCase("BTC")){
            return Cryptocurrency.BTC;
        }
        if (this.baseCurrency.equalsIgnoreCase("ETH")){
            return Cryptocurrency.ETH;
        }
        // Not sure what this is
        throw new IllegalArgumentException("String symbol could not be parsed");
    }

}


package deribit.models.currencies;

import commons.models.currencies.BaseCurrency;
import commons.standards.Cryptocurrency;
import deribit.models.expiries.DeribitExpiry;

import java.util.HashMap;
import java.util.concurrent.ThreadPoolExecutor;

public class DeribitCurrency extends BaseCurrency {

    // This contains the instances present in the system
    private static HashMap<String, DeribitCurrency> currencies;

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

    private static DeribitCurrency getInstance(DeribitCurrency.Builder builder){

        // mAke sure we do have an instance of the HashMap
        if (currencies == null) {
            synchronized (currencies) {
                currencies = new HashMap<>();
            }
        }
        // Already present, just return
        String uniqueIdentifier = builder.getUniqueIdentifier();
        if (currencies.containsKey(uniqueIdentifier)){
            return currencies.get(uniqueIdentifier);
        }

        // Otherwise create it
        else {
            DeribitCurrency newCurrency = new DeribitCurrency(builder);
            synchronized (currencies) {
                currencies.put(builder.getUniqueIdentifier(), newCurrency);
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
        if (this.quoteCurrency.equalsIgnoreCase("BTC")){
            return Cryptocurrency.BTC;
        }
        if (this.quoteCurrency.equalsIgnoreCase("ETH")){
            return Cryptocurrency.ETH;
        }
        // Not sure what this is
        throw new IllegalArgumentException("String symbol could not be parsed");
    }

}


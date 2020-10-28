package deribit.models.currencies;

import commons.models.currencies.BaseCurrency;
import deribit.models.expiries.DeribitExpiry;

import java.util.HashMap;

public class DeribitCurrency extends BaseCurrency {

    // This contains the instances present in the system
    private static HashMap<String, DeribitCurrency> currencies;

    private final int feePrecision;
    private final int minimumConfirmations;
    private final double minimumWithdrawalFee;
    private final double withdrawalFee;

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

        protected int feePrecision;
        protected int minimumConfirmations;
        protected double minimumWithdrawalFee;
        protected double withdrawalFee;

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

        public Builder setFeePrecision(int val) {
            this.feePrecision = val;
            return self();
        }

        public Builder setMinimumConfirmations(int val) {
            this.minimumConfirmations = val;
            return self();
        }

        public Builder setMinimumWithdrawalFee(int val) {
            this.minimumWithdrawalFee = val;
            return self();
        }

        public Builder setWithdrawalFee(int val) {
            this.withdrawalFee = val;
            return self();
        }
    }

}


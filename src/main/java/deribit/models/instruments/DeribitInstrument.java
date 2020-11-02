package deribit.models.instruments;

import commons.models.instruments.BaseInstrument;

import commons.standards.InstrumentKind;
import deribit.models.currencies.DeribitCurrency;
import deribit.models.expiries.DeribitExpiry;
import deribit.models.fees.DeribitFeeStructure;
import deribit.models.contract.DeribitContractStructure;

import java.util.HashMap;


public class DeribitInstrument extends BaseInstrument {

    // This contains the instances present in the system
    private static HashMap<String, DeribitInstrument> instances;

    private final InstrumentKind kind;
    private final DeribitExpiry expiry;
    private final DeribitFeeStructure feeStructure;
    private final DeribitContractStructure marketStructure;

    protected DeribitInstrument(Builder builder)
    {
        super(builder);
        this.kind = builder.kind;
        this.expiry = builder.expiry;
        this.feeStructure = builder.feeStructure;
        this.marketStructure = builder.marketStructure;
    }

    protected static DeribitInstrument getInstance(Builder builder){

        // Make sure we do have an instance of the HashMap
        if (instances == null) {
            synchronized (DeribitInstrument.class) {
                instances = new HashMap<>();
            }
        }

        // Already present, just return
        String uniqueIdentifier = builder.getUniqueIdentifier();
        if (instances.containsKey(uniqueIdentifier)){
            return instances.get(uniqueIdentifier);
        }

        // Otherwise create it
        else {
            DeribitInstrument newInstrument = new DeribitInstrument(builder);
            synchronized (DeribitInstrument.class) {
                instances.put(builder.getUniqueIdentifier(), newInstrument);
            }
            return newInstrument;
        }
    }

    public static class Builder extends BaseInstrument.Builder<Builder>{

        protected InstrumentKind kind;
        protected DeribitExpiry expiry;
        protected DeribitFeeStructure feeStructure;
        protected DeribitContractStructure marketStructure;

        @Override
        protected String getUniqueIdentifier() {
            return this.symbol;
        }

        @Override
        protected DeribitInstrument build() {
            return DeribitInstrument.getInstance(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder setKind(InstrumentKind val) {
            this.kind = val;
            return self();
        }

        public Builder setExpiry(DeribitExpiry val) {
            this.expiry = val;
            return self();
        }

        public Builder setCurrency(DeribitCurrency val) {
            this.currency = val;
            return self();
        }

        public Builder setFeeStructure(DeribitFeeStructure val) {
            this.feeStructure = val;
            return self();
        }

        public Builder setMarketStructure(DeribitContractStructure val) {
            this.marketStructure = val;
            return self();
        }
    }
}


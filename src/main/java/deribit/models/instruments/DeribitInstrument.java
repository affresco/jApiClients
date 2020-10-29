package deribit.models.instruments;

import commons.models.instruments.BaseInstrument;

import commons.standards.InstrumentKind;
import deribit.models.expiries.DeribitExpiry;
import deribit.models.fees.DeribitFeeStructure;
import deribit.models.market.DeribitMarketStructure;

import java.util.HashMap;


public class DeribitInstrument extends BaseInstrument {

    // This contains the instances present in the system
    private static HashMap<String, DeribitInstrument> instances;

    private final InstrumentKind kind;
    private final DeribitExpiry expiry;
    private final DeribitFeeStructure feeStructure;
    private final DeribitMarketStructure marketStructure;

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
            synchronized (instances) {
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

            synchronized (instances) {
                instances.put(builder.getUniqueIdentifier(), newInstrument);
            }
            return newInstrument;
        }
    }

    public static class Builder extends BaseInstrument.Builder<Builder>{

        protected InstrumentKind kind;
        protected DeribitExpiry expiry;
        protected DeribitFeeStructure feeStructure;
        protected DeribitMarketStructure marketStructure;

        @Override
        protected String getUniqueIdentifier() {
            return this.symbol;
        }

        @Override
        protected DeribitInstrument build() {
            return null;
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

        public Builder setFeeStructure(DeribitFeeStructure val) {
            this.feeStructure = val;
            return self();
        }

        public Builder setMarketStructure(DeribitMarketStructure val) {
            this.marketStructure = val;
            return self();
        }
    }
}


/*

      // Local fields
      "kind": "future",
      "instrument_name": "BTC-26JUL19",

      // Into expiry object
      "is_active": true,
      "settlement_period": "week",
      "expiration_timestamp": 1564153200000,
      "creation_timestamp": 1563522420000,

      // into TradingCharacteristics object
      "min_trade_amount": 1,
      "leverage": 100,
      "contract_size": 10,
      "tick_size": 0.01,

      // into Currency object
      "base_currency": "BTC"
      "quote_currency": "USD",
 */

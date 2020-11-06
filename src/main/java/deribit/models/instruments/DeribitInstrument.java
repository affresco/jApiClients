package deribit.models.instruments;

import commons.models.instruments.BaseInstrument;

import commons.standards.InstrumentKind;
import deribit.models.currencies.DeribitCurrency;
import deribit.models.expiries.DeribitExpiry;
import deribit.models.fees.DeribitFeeStructure;
import deribit.models.contract.DeribitContractStructure;

import java.util.HashMap;


public abstract class DeribitInstrument extends BaseInstrument {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private final InstrumentKind kind;
    private final DeribitExpiry expiry;
    private final DeribitCurrency currency;
    private final DeribitFeeStructure feeStructure;
    private final DeribitContractStructure contractStructure;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    protected DeribitInstrument(Builder builder)
    {
        super(builder);
        this.kind = builder.kind;
        this.expiry = builder.expiry;
        this.currency = builder.currency;
        this.feeStructure = builder.feeStructure;
        this.contractStructure = builder.contractStructure;
    }

    // ##################################################################
    // BUILD (STATIC CLASS)
    // ##################################################################

    public static class Builder extends BaseInstrument.Builder<Builder>{

        protected InstrumentKind kind;
        protected DeribitExpiry expiry;
        protected DeribitCurrency currency;
        protected DeribitFeeStructure feeStructure;
        protected DeribitContractStructure contractStructure;

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
            return null;
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

        public Builder setContractStructure(DeribitContractStructure val) {
            this.contractStructure = val;
            return self();
        }
    }

    // ##################################################################
    // GETTERS
    // ##################################################################


    public DeribitContractStructure getContractStructure() {
        return new DeribitContractStructure(contractStructure);
    }

    public DeribitExpiry getExpiry() {
        return new DeribitExpiry(expiry);
    }

    public DeribitFeeStructure getFeeStructure() {
        return new DeribitFeeStructure(feeStructure);
    }

    public InstrumentKind getKind() {
        return kind;
    }

    public DeribitCurrency getCurrency() {
        return new DeribitCurrency(currency);
    }
}


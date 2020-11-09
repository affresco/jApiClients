package deribit.models.contract;

import commons.models.contract.BaseContractStructure;

public class DeribitContractStructure extends BaseContractStructure {

    private final double minimumTradeAmount;
    private final double leverage;
    private final double contractSize;
    private final double tickSize;

    protected DeribitContractStructure(Builder builder) {
        super(builder);
        this.minimumTradeAmount = builder.minimumTradeAmount;
        this.leverage = builder.leverage;
        this.contractSize = builder.contractSize;
        this.tickSize = builder.tickSize;
    }

    public DeribitContractStructure(DeribitContractStructure contractStructure) {
        super(contractStructure);
        this.minimumTradeAmount = contractStructure.getMinimumTradeAmount();
        this.leverage = contractStructure.getLeverage();
        this.contractSize = contractStructure.getContractSize();
        this.tickSize = contractStructure.getTickSize();
    }

    public static class Builder extends BaseContractStructure.Builder<Builder>{

        protected double minimumTradeAmount;
        protected double leverage;
        protected double contractSize;
        protected double tickSize;

        @Override
        public DeribitContractStructure build() {
            return new DeribitContractStructure(this);
        }

        @Override
        protected Builder self() {
            return this;
        }


        public DeribitContractStructure.Builder setMinimumTradeAmount(double val) {
            this.minimumTradeAmount = val;
            return self();
        }

        public DeribitContractStructure.Builder setLeverage(double val) {
            this.leverage = val;
            return self();
        }

        public DeribitContractStructure.Builder setContractSize(double val) {
            this.contractSize = val;
            return self();
        }

        public DeribitContractStructure.Builder setTickSize(double val) {
            this.tickSize = val;
            return self();
        }
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public double getContractSize() {
        return contractSize;
    }

    public double getLeverage() {
        return leverage;
    }

    public double getTickSize() {
        return tickSize;
    }

    public double getMinimumTradeAmount() {
        return minimumTradeAmount;
    }
}

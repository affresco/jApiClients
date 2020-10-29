package deribit.models.market;

import commons.models.market.BaseMarketStructure;

public class DeribitMarketStructure extends BaseMarketStructure {

    private final Double minimumTradeAmount;
    private final Double leverage;
    private final Double contractSize;
    private final Double tickSize;

    protected DeribitMarketStructure(Builder builder) {
        super(builder);
        this.minimumTradeAmount = builder.minimumTradeAmount;
        this.leverage = builder.leverage;
        this.contractSize = builder.contractSize;
        this.tickSize = builder.tickSize;
    }

    public static class Builder extends BaseMarketStructure.Builder<Builder>{

        protected Double minimumTradeAmount;
        protected Double leverage;
        protected Double contractSize;
        protected Double tickSize;

        @Override
        public DeribitMarketStructure build() {
            return new DeribitMarketStructure(this);
        }

        @Override
        protected Builder self() {
            return this;
        }


        public DeribitMarketStructure.Builder setMinimumTradeAmount(Double val) {
            this.minimumTradeAmount = val;
            return self();
        }

        public DeribitMarketStructure.Builder setLeverage(Double val) {
            this.leverage = val;
            return self();
        }

        public DeribitMarketStructure.Builder setContractSize(Double val) {
            this.contractSize = val;
            return self();
        }

        public DeribitMarketStructure.Builder setTickSize(Double val) {
            this.tickSize = val;
            return self();
        }
    }

}

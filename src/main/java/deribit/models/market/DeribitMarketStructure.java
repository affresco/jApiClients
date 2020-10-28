package deribit.models.market;

import commons.models.market.BaseMarketStructure;

public class DeribitMarketStructure extends BaseMarketStructure {

    private final double minimumTradeAmount;
    private final double leverage;
    private final double contractSize;
    private final double tickSize;

    protected DeribitMarketStructure(Builder builder) {
        super(builder);
        this.minimumTradeAmount = builder.minimumTradeAmount;
        this.leverage = builder.leverage;
        this.contractSize = builder.contractSize;
        this.tickSize = builder.tickSize;
    }

    public static class Builder extends BaseMarketStructure.Builder<Builder>{

        protected double minimumTradeAmount;
        protected double leverage;
        protected double contractSize;
        protected double tickSize;

        @Override
        public BaseMarketStructure build() {
            return new DeribitMarketStructure(this);
        }

        @Override
        protected Builder self() {
            return this;
        }


        public DeribitMarketStructure.Builder setMinimumTradeAmount(int val) {
            this.minimumTradeAmount = val;
            return self();
        }

        public DeribitMarketStructure.Builder setLeverage(int val) {
            this.leverage = val;
            return self();
        }

        public DeribitMarketStructure.Builder setContractSize(int val) {
            this.contractSize = val;
            return self();
        }

        public DeribitMarketStructure.Builder setTickSize(int val) {
            this.tickSize = val;
            return self();
        }
    }

}

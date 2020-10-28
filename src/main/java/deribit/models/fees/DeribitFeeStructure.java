package deribit.models.fees;

import commons.models.fees.BaseFeeStructure;

public class DeribitFeeStructure extends BaseFeeStructure {

    private final double liquidityTaker;
    private final double liquidityMaker;

    protected DeribitFeeStructure(Builder builder) {
        super(builder);
        this.liquidityTaker = builder.liquidityTaker;
        this.liquidityMaker = builder.liquidityMaker;
    }


    public static class Builder extends BaseFeeStructure.Builder<Builder>{

        protected double liquidityTaker;
        protected double liquidityMaker;

        @Override
        public BaseFeeStructure build() {
            return new DeribitFeeStructure(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder setLiquidityTaker(int val) {
            this.liquidityTaker = val;
            return self();
        }

        public Builder setLiquidityMaker(int val) {
            this.liquidityMaker = val;
            return self();
        }
    }

}

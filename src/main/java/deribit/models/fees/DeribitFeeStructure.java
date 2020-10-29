package deribit.models.fees;

import commons.models.fees.BaseFeeStructure;

public class DeribitFeeStructure extends BaseFeeStructure {

    private final Double liquidityTaker;
    private final Double liquidityMaker;

    protected DeribitFeeStructure(Builder builder) {
        super(builder);
        this.liquidityTaker = builder.liquidityTaker;
        this.liquidityMaker = builder.liquidityMaker;
    }


    public static class Builder extends BaseFeeStructure.Builder<Builder>{

        protected Double liquidityTaker;
        protected Double liquidityMaker;

        @Override
        public DeribitFeeStructure build() {
            return new DeribitFeeStructure(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder setLiquidityTaker(Double val) {
            this.liquidityTaker = val;
            return self();
        }

        public Builder setLiquidityMaker(Double val) {
            this.liquidityMaker = val;
            return self();
        }
    }

}

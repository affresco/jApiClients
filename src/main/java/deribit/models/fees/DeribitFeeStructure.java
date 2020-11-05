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

    public DeribitFeeStructure(DeribitFeeStructure feeStructure){
        super(feeStructure);
        this.liquidityTaker = feeStructure.getLiquidityTaker();
        this.liquidityMaker = feeStructure.getLiquidityMaker();
    }

    public static class Builder extends BaseFeeStructure.Builder<Builder>{

        protected double liquidityTaker;
        protected double liquidityMaker;

        @Override
        public DeribitFeeStructure build() {
            return new DeribitFeeStructure(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder setLiquidityTaker(double val) {
            this.liquidityTaker = val;
            return self();
        }

        public Builder setLiquidityMaker(double val) {
            this.liquidityMaker = val;
            return self();
        }
    }

    public double getLiquidityMaker() {
        return liquidityMaker;
    }

    public double getLiquidityTaker() {
        return liquidityTaker;
    }
}

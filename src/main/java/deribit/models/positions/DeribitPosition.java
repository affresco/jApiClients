package deribit.models.positions;

import commons.models.positions.BasePosition;

public class DeribitPosition extends BasePosition {

    // Content
    private double totalProfitLoss;
    private double sizeCurrency;
    private double size;
    private double settlementPrice;
    private double realizedProfitLoss;
    private double realizedFunding;
    private double openOrdersMargin;
    private double markPrice;
    private double maintenanceMargin;
    private double leverage;
    private String kind;
    private String instrument;
    private double initialMargin;
    private double indexPrice;
    private double floatingProfitLoss;
    private double estimatedLiquidationPrice;
    private String direction;
    private double delta;
    private double averagePrice;

    public double getTotalProfitLoss() {
        return totalProfitLoss;
    }

    public double getSizeCurrency() {
        return sizeCurrency;
    }

    public double getSize() {
        return size;
    }

    public double getSettlementPrice() {
        return settlementPrice;
    }

    public double getRealizedProfitLoss() {
        return realizedProfitLoss;
    }

    public double getRealizedFunding() {
        return realizedFunding;
    }

    public double getOpenOrdersMargin() {
        return openOrdersMargin;
    }

    public double getMarkPrice() {
        return markPrice;
    }

    public double getMaintenanceMargin() {
        return maintenanceMargin;
    }

    public double getLeverage() {
        return leverage;
    }

    public String getKind() {
        return kind;
    }

    public String getInstrument() {
        return instrument;
    }

    public double getInitialMargin() {
        return initialMargin;
    }

    public double getIndexPrice() {
        return indexPrice;
    }

    public double getFloatingProfitLoss() {
        return floatingProfitLoss;
    }

    public double getEstimatedLiquidationPrice() {
        return estimatedLiquidationPrice;
    }

    public String getDirection() {
        return direction;
    }

    public double getDelta() {
        return delta;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    protected DeribitPosition(Builder builder)
    {
        super(builder);

        this.totalProfitLoss = builder.totalProfitLoss;
        this.sizeCurrency = builder.sizeCurrency;
        this.size = builder.size;
        this.settlementPrice = builder.settlementPrice;

        this.realizedProfitLoss = builder.realizedProfitLoss;
        this.realizedFunding = builder.realizedFunding;
        this.openOrdersMargin = builder.openOrdersMargin;
        this.markPrice = builder.markPrice;

        this.maintenanceMargin = builder.maintenanceMargin;
        this.leverage = builder.leverage;
        this.kind = builder.kind;
        this.instrument = builder.instrument;

        this.initialMargin = builder.initialMargin;
        this.indexPrice = builder.indexPrice;
        this.floatingProfitLoss = builder.floatingProfitLoss;
        this.estimatedLiquidationPrice = builder.estimatedLiquidationPrice;

        this.direction = builder.direction;
        this.delta = builder.delta;
        this.averagePrice = builder.averagePrice;
    }

    public static class Builder extends BasePosition.Builder<Builder>{

        // Content
        private double totalProfitLoss;
        private double sizeCurrency;
        private double size;
        private double settlementPrice;

        private double realizedProfitLoss;
        private double realizedFunding;
        private double openOrdersMargin;
        private double markPrice;

        private double maintenanceMargin;
        private double leverage;
        private String kind;
        private String instrument;

        private double initialMargin;
        private double indexPrice;
        private double floatingProfitLoss;
        private double estimatedLiquidationPrice;

        private String direction;
        private double delta;
        private double averagePrice;

        @Override
        public DeribitPosition build() {
            return new DeribitPosition(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder setSizeCurrency(double val) {
            this.sizeCurrency = val;
            return self();
        }

        public Builder setSize(double val) {
            this.size = val;
            return self();
        }

        public Builder setSettlementPrice(double val) {
            this.settlementPrice = val;
            return self();
        }

        public Builder setRealizedProfitLoss(double val) {
            this.realizedProfitLoss = val;
            return self();
        }

        public Builder setTotalProfitLoss(double val) {
            this.totalProfitLoss = val;
            return self();
        }

        public Builder setRealizedFunding(double val) {
            this.realizedFunding = val;
            return self();
        }

        public Builder setOpenOrdersMargin(double val) {
            this.openOrdersMargin = val;
            return self();
        }

        public Builder setMarkPrice(double val) {
            this.markPrice = val;
            return self();
        }

        public Builder setMaintenanceMargin(double val) {
            this.maintenanceMargin = val;
            return self();
        }

        public Builder setLeverage(double val) {
            this.leverage = val;
            return self();
        }

        public Builder setKind(String val) {
            this.kind = val;
            return self();
        }

        public Builder setInstrument(String val) {
            this.instrument = val;
            return self();
        }

        public Builder setInitialMargin(int val) {
            this.initialMargin = val;
            return self();
        }

        public Builder setIndexPrice(int val) {
            this.indexPrice = val;
            return self();
        }

        public Builder setFloatingProfitLoss(double val) {
            this.floatingProfitLoss = val;
            return self();
        }

        public Builder setEstimatedLiquidationPrice(double val) {
            this.estimatedLiquidationPrice = val;
            return self();
        }

        public Builder setDirection(String val) {
            this.direction = val;
            return self();
        }

        public Builder setDelta(int val) {
            this.delta = val;
            return self();
        }

        public Builder setAveragePrice(double val) {
            this.averagePrice = val;
            return self();
        }


    }

}


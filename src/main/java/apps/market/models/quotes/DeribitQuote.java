package apps.market.models.quotes;

import deribit.models.instruments.DeribitInstrument;
import commons.models.exchange.DerivativeExchange;
import commons.models.quotes.Quote;

public class DeribitQuote implements Quote {

    private final DerivativeExchange exchange = DerivativeExchange.DERIBIT;
    private final DeribitInstrument instrument;
    private final double bidPrice;
    private final double askPrice;
    private final double bidQuantity;
    private final double askQuantity;
    private final double timestamp;

    public DeribitQuote(){
        this.instrument = null;
        this.bidPrice = 0.0;
        this.askPrice = 0.0;
        this.bidQuantity = 0.0;
        this.askQuantity = 0.0;
        this.timestamp = 0.0;
    }

    protected DeribitQuote(DeribitQuote.Builder builder){
        this.instrument = builder.instrument;
        this.bidPrice = builder.bidPrice;
        this.askPrice = builder.askPrice;
        this.bidQuantity = builder.bidQuantity;
        this.askQuantity = builder.askQuantity;
        this.timestamp = builder.timestamp;
    }

    public DeribitQuote(DeribitInstrument instrument, double bidPrice, double askPrice, double bidQuantity, double askQuantity, double timestamp){
        this.instrument = instrument;
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
        this.bidQuantity = bidQuantity;
        this.askQuantity = askQuantity;
        this.timestamp = timestamp;
    }

    @Override
    public DeribitInstrument getInstrument() {
        return instrument;
    }

    @Override
    public double getBidPrice() {
        return bidPrice;
    }

    @Override
    public double getAskPrice() {
        return askPrice;
    }

    @Override
    public double getBidQuantity() {
        return bidQuantity;
    }

    @Override
    public double getAskQuantity() {
        return askQuantity;
    }

    @Override
    public double getTimestamp() {
        return timestamp;
    }

    @Override
    public DerivativeExchange getExchange() {
        return exchange;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    public static class Builder {
        private DeribitInstrument instrument;
        private double bidPrice;
        private double askPrice;
        private double bidQuantity;
        private double askQuantity;
        private double timestamp;

        public DeribitQuote build() {
            return new DeribitQuote(this);
        }

        protected Builder self() {
            return this;
        }

        public Builder setInstrument(DeribitInstrument val) {
            this.instrument = val;
            return self();
        }

        public Builder setBidPrice(double val) {
            this.bidPrice = val;
            return self();
        }

        public Builder setBidPrice(String val) {
            this.bidPrice = Double.parseDouble(val);
            return self();
        }

        public Builder setAskPrice(double val) {
            this.askPrice = val;
            return self();
        }

        public Builder setAskPrice(String val) {
            this.askPrice = Double.parseDouble(val);
            return self();
        }

        public Builder setBidQuantity(double val) {
            this.bidQuantity = val;
            return self();
        }

        public Builder setBidQuantity(String val) {
            this.bidQuantity = Double.parseDouble(val);
            return self();
        }

        public Builder setAskQuantity(double val) {
            this.askQuantity = val;
            return self();
        }

        public Builder setAskQuantity(String val) {
            this.askQuantity = Double.parseDouble(val);
            return self();
        }

        public Builder setTimestamp(double val) {
            this.timestamp = val;
            return self();
        }

        public Builder setTimestamp(String val) {
            this.timestamp = Double.parseDouble(val);
            return self();
        }

    }

}






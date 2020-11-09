package apps.market.models.quotes;

import commons.standards.Cryptocurrency;
import deribit.models.instruments.DeribitInstrument;
import commons.standards.DerivativeExchange;
import commons.models.quotes.Quote;

public class DeribitQuote implements Quote {

    // ##################################################################
    // PROPERTIES
    // ##################################################################

    private final DerivativeExchange exchange = DerivativeExchange.DERIBIT;
    private final Cryptocurrency currency;
    private final DeribitInstrument instrument;
    private final double bidPrice;
    private final double askPrice;
    private final double bidQuantity;
    private final double askQuantity;
    private final double timestamp;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public DeribitQuote() {
        this.instrument = null;
        this.currency = null;
        this.bidPrice = Double.NaN;
        this.askPrice = Double.NaN;
        this.bidQuantity = Double.NaN;
        this.askQuantity = Double.NaN;
        this.timestamp = Double.NaN;
    }

    protected DeribitQuote(DeribitQuote.Builder builder) {
        this.instrument = builder.instrument;
        this.currency = builder.currency;
        this.bidPrice = builder.bidPrice;
        this.askPrice = builder.askPrice;
        this.bidQuantity = builder.bidQuantity;
        this.askQuantity = builder.askQuantity;
        this.timestamp = builder.timestamp;
    }

    public DeribitQuote(DeribitInstrument instrument, Cryptocurrency currency, double bidPrice, double askPrice, double bidQuantity, double askQuantity, double timestamp) {
        this.instrument = instrument;
        this.currency = currency;
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
        this.bidQuantity = bidQuantity;
        this.askQuantity = askQuantity;
        this.timestamp = timestamp;
    }
    // ##################################################################
    // INTERFACE
    // ##################################################################

    @Override
    public DeribitInstrument getInstrument() {
        return instrument;
    }

    @Override
    public Double getBidPrice() {
        return bidPrice;
    }

    @Override
    public Double getAskPrice() {
        return askPrice;
    }

    @Override
    public Double getBidQuantity() {
        return bidQuantity;
    }

    @Override
    public Double getAskQuantity() {
        return askQuantity;
    }

    @Override
    public Double getTimestamp() {
        return timestamp;
    }

    @Override
    public DerivativeExchange getExchange() {
        return exchange;
    }

    public Cryptocurrency getCurrency() {
        return currency;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    // ##################################################################
    // BUILDER STATIC CLASS
    // ##################################################################

    public static class Builder {
        private DeribitInstrument instrument;
        private Cryptocurrency currency;
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

        public Builder setAskPrice(double val) {
            this.askPrice = val;
            return self();
        }

        public Builder setBidQuantity(double val) {
            this.bidQuantity = val;
            return self();
        }

        public Builder setAskQuantity(double val) {
            this.askQuantity = val;
            return self();
        }

        public Builder setTimestamp(double val) {
            this.timestamp = val;
            return self();
        }

        public Builder setCurrency(Cryptocurrency val) {
            this.currency = val;
            return self();
        }
    }

}






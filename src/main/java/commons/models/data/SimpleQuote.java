package commons.models.data;

import commons.models.instruments.BaseInstrument;

public class SimpleQuote {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    protected final Double bidPrice;
    protected final Double bidQuantity;
    protected final Double askPrice;
    protected final Double askQuantity;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public SimpleQuote(Double bidPrice, Double askPrice, Double bidQuantity, Double askQuantity) {
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
        this.bidQuantity = bidQuantity;
        this.askQuantity = askQuantity;
    }

    public SimpleQuote(SimpleQuote q) {
        this.bidPrice = q.getBidPrice();
        this.askPrice = q.getAskPrice();
        this.bidQuantity = q.getBidQuantity();
        this.askQuantity = q.getAskQuantity();
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public Double getAskPrice() {
        return askPrice;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public Double getMidPrice() {
        return 0.5 * (askPrice + bidPrice);
    }

    public Double getAskQuantity() {
        return askQuantity;
    }

    public Double getBidQuantity() {
        return bidQuantity;
    }

    // ##################################################################
    // REPRESENTATION
    // ##################################################################

    @Override
    public String toString() {
        return "SimpleQuote";
    }
}

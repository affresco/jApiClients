package apps.market.events;

import apps.market.models.quotes.DeribitQuote;

public class DeribitQuoteEvent implements MarketDataEvent {

    private final DeribitQuote quote;

    public DeribitQuoteEvent(DeribitQuote quote){
        this.quote = quote;
    }

    public DeribitQuote getQuote() {
        return quote;
    }
}

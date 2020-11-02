package apps.market.events;

import apps.market.models.quotes.DeribitQuote;

public class DeribitIndexEvent implements MarketDataEvent {

    private final DeribitQuote quote;

    public DeribitIndexEvent(DeribitQuote quote){
        this.quote = quote;
    }

}

package jClient.delta.endpoints;

public enum MarketEndpoints
{
    GET_INSTRUMENTS("public/get_instruments"),
    GET_ORDER_BOOK("public/get_order_book"),
    CURRENCIES("public/get_currencies"),
    INDEX("public/get_index"),
    LAST_TRADES_BY_INSTRUMENT("public/get_last_trades_by_instrument"),
    BOOK_SUMMARY_BY_CURRENCY("public/get_book_summary_by_currency"),
    BOOK_SUMMARY_BY_INSTRUMENT("public/get_book_summary_by_instrument");

    private final String endpoint;

    MarketEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}

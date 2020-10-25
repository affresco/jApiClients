package jClient.delta.endpoints;

public enum TradingEndpoints {

    BUY("private/buy"),
    SELL("private/sell"),
    EDIT("private/edit"),
    CLOSE_POSITION("private/close_position"),

    // Order cancellation
    CANCEL_ALL("private/cancel_all"),
    CANCEL_ALL_BY_CURRENCY("private/cancel_all_by_currency"),
    CANCEL_ALL_BY_INSTRUMENT("private/cancel_all_by_instrument"),

    CANCEL_BY_ID("private/cancel"),
    CANCEL_BY_LABEL("private/cancel_by_label"),

    // Margins
    COMPUTE_MARGIN("private/get_margins"),

    // Open models status
    ORDER_STATUS("private/get_order_state"),
    OPEN_ORDERS_BY_CURRENCY("private/get_open_orders_by_currency"),
    OPEN_ORDERS_BY_INSTRUMENT("private/get_open_orders_by_instrument"),

    // Order history (user)
    USER_ORDER_HISTORY_BY_CURRENCY("private/get_order_history_by_currency"),
    USER_ORDER_HISTORY_BY_INSTRUMENT("private/get_order_history_by_instrument"),

    // Trade history (user)
    USER_TRADES_BY_CURRENCY("private/get_user_trades_by_currency"),
    USER_TRADES_BY_INSTRUMENT("private/get_user_trades_by_instrument");


    private final String endpoint;

    TradingEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}

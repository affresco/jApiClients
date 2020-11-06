package commons.standards;

public enum DerivativeExchange {

    DERIBIT(0),
    OKEX(1),
    HUOBI(2),
    QUEDEX(3),
    LEDGER_X(4),
    BIT_DOT_COM(5);

    private final int endpoint;

    DerivativeExchange(int endpoint) {
        this.endpoint = endpoint;
    }

    public int getEndpoint() {
        return endpoint;
    }
}

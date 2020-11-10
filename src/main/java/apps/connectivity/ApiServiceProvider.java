package apps.connectivity;

public enum ApiServiceProvider {

    AFFRESCO("Affresco"),
    DERIBIT("Deribit"),
    OKEX("Okex"),
    HUOBI("Huobi"),
    QUEDEX("Quedex"),
    LEDGER_X("LedgerX"),
    BIT_DOT_COM("Bit.com");

    private final String value;

    ApiServiceProvider(String value) {
        this.value = value;
    }

    public String getFullName() {
        return value;
    }
}

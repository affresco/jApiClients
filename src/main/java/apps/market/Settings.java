package apps.market;

public enum Settings {

    NAME("Market Data"),
    SYMBOL("MKT");

    private final String value;

    Settings(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package apps.connectivity;

public enum Settings {

    NAME("Connectivity"),
    SYMBOL("CON");

    private final String value;

    Settings(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

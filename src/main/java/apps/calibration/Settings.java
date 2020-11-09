package apps.calibration;

public enum Settings {

    NAME("Calibration"),

    SYMBOL("CAL");

    private final String value;

    Settings(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package apps.execution;

public enum Settings {

    NAME("Execution"),
    SYMBOL("EXE");

    private final String value;

    Settings(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

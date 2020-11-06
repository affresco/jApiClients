package commons.standards;

public enum TimeInForce
{
    GOOD_TIL_CANCELLED(0),
    FILL_OR_KILL(1),
    IMMEDIATE_OR_CANCEL(2);

    private final int timeInForce;

    TimeInForce(int timeInForce) {
        this.timeInForce = timeInForce;
    }

    public int getIntegerRepresentation() {
        return timeInForce;
    }
}


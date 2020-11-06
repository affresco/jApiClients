package commons.standards;

public enum TickDirection {

    PLUS(0),
    ZERO_PLUS(1),
    MINUS(2),
    ZERO_MINUS(3);


    private final int direction;

    TickDirection(int direction) {
        this.direction = direction;
    }

    public int getIntegerRepresentation() {
        return direction;
    }
}

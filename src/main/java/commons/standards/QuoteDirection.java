package commons.standards;

public enum QuoteDirection
{

    BID(1),
    ZERO(0),
    ASK(-1);

    private final int direction;

    QuoteDirection(int direction) {
        this.direction = direction;
    }

    public int getIntegerRepresentation() {
        return direction;
    }
}

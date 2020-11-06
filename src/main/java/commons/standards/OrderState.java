package commons.standards;

public enum OrderState
{
    OPEN(0),
    CLOSE(1),
    FILLED(2),
    REJECTED(3),
    CANCELLED(4),
    UNTRIGGERED(5),
    ARCHIVED(6),
    TRIGGERED(7);
    
    private final int orderState;

    OrderState(int orderState) {
        this.orderState = orderState;
    }

    public int getIntegerRepresentation() {
        return orderState;
    }
}


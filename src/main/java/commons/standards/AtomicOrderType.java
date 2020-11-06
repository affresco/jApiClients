package commons.standards;

public enum AtomicOrderType
{
    LIMIT(0),
    MARKET(1),
    LIQUIDATION(2),
    STOP_LIMIT(3),
    STOP_MARKET(4);

    private final int orderType;

    AtomicOrderType(int orderType) {
        this.orderType = orderType;
    }

    public int getIntegerRepresentation() {
        return orderType;
    }

    public static AtomicOrderType getInstance(String orderType) {

        if(orderType.equalsIgnoreCase("limit")){
            return AtomicOrderType.LIMIT;
        }

        if(orderType.equalsIgnoreCase("market")){
            return AtomicOrderType.MARKET;
        }

        if(orderType.equalsIgnoreCase("liquidation")){
            return AtomicOrderType.LIQUIDATION;
        }

        if(orderType.contains("stop") && orderType.contains("limit") ){
            return AtomicOrderType.STOP_LIMIT;
        }

        if(orderType.contains("stop") && orderType.contains("market") ){
            return AtomicOrderType.STOP_MARKET;
        }

        throw new IllegalArgumentException("Cannot infer atomic order type from string: " + orderType);

    }
}

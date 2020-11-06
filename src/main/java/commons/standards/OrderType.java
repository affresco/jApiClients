package commons.standards;

public enum OrderType
{
    LIMIT(0),
    MARKET(1),
    LIQUIDATION(2),
    STOP_LIMIT(3),
    STOP_MARKET(4),
    SPREAD_OVER_TIME(5),
    LIMIT_BOTH_SIDES(6);

    private final int orderType;

    OrderType(int orderType) {
        this.orderType = orderType;
    }

    public int getIntegerRepresentation() {
        return orderType;
    }

    public static OrderType getInstance(String orderType) {

        if(orderType.equalsIgnoreCase("limit")){
            return OrderType.LIMIT;
        }

        if(orderType.equalsIgnoreCase("market")){
            return OrderType.MARKET;
        }

        if(orderType.equalsIgnoreCase("liquidation")){
            return OrderType.LIQUIDATION;
        }

        if(orderType.contains("stop") && orderType.contains("limit") ){
            return OrderType.STOP_LIMIT;
        }

        if(orderType.contains("stop") && orderType.contains("market") ){
            return OrderType.STOP_MARKET;
        }

        if(orderType.equalsIgnoreCase("spread")){
            return OrderType.SPREAD_OVER_TIME;
        }

        if(orderType.contains("both") && orderType.contains("side") ){
            return OrderType.LIMIT_BOTH_SIDES;
        }

        throw new IllegalArgumentException("Cannot infer order type from string: " + orderType);

    }


}

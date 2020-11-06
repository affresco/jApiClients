package commons.standards;

public enum LiquidityType
{
    MAKER(0),
    TAKER(1);
    
    private final int liquidityType;

    LiquidityType(int liquidityType) {
        this.liquidityType = liquidityType;
    }

    public int getIntegerRepresentation() {
        return liquidityType;
    }
}


package commons.standards;


public enum Cryptocurrency
{
    BTC(0),
    ETH(1);

    private final int integerRepresentation;

    Cryptocurrency(int integerRepresentation) {
        this.integerRepresentation = integerRepresentation;
    }

    public int getIntegerRepresentation() {
        return integerRepresentation;
    }

    public boolean equalsIgnoreCase(String other){
        return other.equalsIgnoreCase(this.toString());
    }
}

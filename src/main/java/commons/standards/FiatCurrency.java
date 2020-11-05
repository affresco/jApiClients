package commons.standards;

public enum FiatCurrency
{
    USD(0),
    EUR(1);

    private final int integerRepresentation;

    FiatCurrency(int integerRepresentation) {
        this.integerRepresentation = integerRepresentation;
    }

    public int getIntegerRepresentation() {
        return integerRepresentation;
    }

    public boolean equalsIgnoreCase(String other){
        return other.equalsIgnoreCase(this.toString());
    }

}

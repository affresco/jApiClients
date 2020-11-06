package commons.standards;

public enum OptionType
{
    CALL(0),
    PUT(1);
  
    private final int optionType;

    OptionType(int optionType) {
        this.optionType = optionType;
    }

    public int getIntegerRepresentation() {
        return optionType;
    }
}

package deribit.models.currencies.defaults;

public class EtherDefaults {

    private EtherDefaults(){}

    public final static String NAME = "Ether";
    public final static String SYMBOL = "ETH";
    public final static String BASE_CURRENCY = "ETH";
    public final static String QUOTE_CURRENCY = "USD";

    public final static int FEE_PRECISION = 4;
    public final static double WITHDRAWAL_FEE = 0.0001;
    public final static int MINIMUM_CONFIRMATIONS = 4;
    public final static double MINIMUM_WITHDRAWAL_FEE = 0.0001;

}

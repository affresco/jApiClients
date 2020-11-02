package deribit.models.currencies.defaults;

public class BitcoinDefaults {

    private BitcoinDefaults(){}

    public final static String NAME = "Bitcoin";
    public final static String SYMBOL = "BTC";
    public final static String BASE_CURRENCY = "BTC";
    public final static String QUOTE_CURRENCY = "USD";

    public final static int FEE_PRECISION = 4;
    public final static double WITHDRAWAL_FEE = 0.0001;
    public final static int MINIMUM_CONFIRMATIONS = 1;
    public final static double MINIMUM_WITHDRAWAL_FEE = 0.0001;

}

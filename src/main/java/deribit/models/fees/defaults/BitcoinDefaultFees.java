package deribit.models.fees.defaults;

public class BitcoinDefaultFees {

    private BitcoinDefaultFees(){}

    public static final double FUTURE_TAKER = 0.0005;
    public static final double FUTURE_MAKER = 0.0;

    public static final double OPTION_TAKER_FROM_UNDERLYING = 0.0003;
    public static final double OPTION_TAKER_FROM_OPTION_PRICE = 0.0003;
    public static final double OPTION_TAKER_MAXIMUM_FROM_UNDERLYING = 0.125;

    public static final double OPTION_MAKER_FROM_UNDERLYING = 0.0003;
    public static final double OPTION_MAKER_FROM_OPTION_PRICE = 0.0003;
    public static final double OPTION_MAKER_MAXIMUM_FROM_UNDERLYING = 0.125;

}

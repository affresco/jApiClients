package deribit.models.currencies.defaults;

import commons.standards.Cryptocurrency;
import commons.standards.FiatCurrency;

public class EtherDefaults {

    private EtherDefaults(){}

    public final static String NAME = "Ether";
    public final static String SYMBOL = "ETH";
    public final static Cryptocurrency BASE_CURRENCY = Cryptocurrency.ETH;
    public final static FiatCurrency QUOTE_CURRENCY = FiatCurrency.USD;


    public final static int FEE_PRECISION = 4;
    public final static double WITHDRAWAL_FEE = 0.0001;
    public final static int MINIMUM_CONFIRMATIONS = 4;
    public final static double MINIMUM_WITHDRAWAL_FEE = 0.0001;

}

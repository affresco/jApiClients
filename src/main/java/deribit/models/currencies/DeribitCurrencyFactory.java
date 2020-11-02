package deribit.models.currencies;

import deribit.models.currencies.defaults.BitcoinDefaults;
import deribit.models.currencies.defaults.EtherDefaults;

public class DeribitCurrencyFactory {

    private DeribitCurrencyFactory() {
    }

    public static DeribitCurrency getInstanceFromSymbol(String symbol) {

        // Make sure the format is right
        String s = symbol.toUpperCase();

        // This handles the perpetual
        if (s.contains("BTC")){
            return DeribitCurrencyFactory.getBitcoin(s);
        }

        // This handles the perpetual
        if (s.contains("ETH")){
            return DeribitCurrencyFactory.getEther(s);
        }

        throw new IllegalArgumentException("Symbol could not be parsed into a currency: " + s);
    }

    private static DeribitCurrency getBitcoin(String s) {

        DeribitCurrency.Builder builder = new DeribitCurrency.Builder();
        return builder
                .setName(BitcoinDefaults.NAME)
                .setSymbol(BitcoinDefaults.SYMBOL)
                .setBaseCurrency(BitcoinDefaults.BASE_CURRENCY)
                .setQuoteCurrency(BitcoinDefaults.QUOTE_CURRENCY)

                // Default values
                .setFeePrecision(BitcoinDefaults.FEE_PRECISION)
                .setWithdrawalFee(BitcoinDefaults.WITHDRAWAL_FEE)
                .setMinimumConfirmations(BitcoinDefaults.MINIMUM_CONFIRMATIONS)
                .setMinimumWithdrawalFee(BitcoinDefaults.MINIMUM_WITHDRAWAL_FEE)

                .build();
    }

    private static DeribitCurrency getEther(String s) {

        DeribitCurrency.Builder builder = new DeribitCurrency.Builder();
        return builder
                .setName(EtherDefaults.NAME)
                .setSymbol(EtherDefaults.SYMBOL)
                .setBaseCurrency(EtherDefaults.BASE_CURRENCY)
                .setQuoteCurrency(EtherDefaults.QUOTE_CURRENCY)

                // Default values: null
                .setFeePrecision(EtherDefaults.FEE_PRECISION)
                .setWithdrawalFee(EtherDefaults.WITHDRAWAL_FEE)
                .setMinimumConfirmations(EtherDefaults.MINIMUM_CONFIRMATIONS)
                .setMinimumWithdrawalFee(EtherDefaults.MINIMUM_WITHDRAWAL_FEE)

                .build();
    }

}

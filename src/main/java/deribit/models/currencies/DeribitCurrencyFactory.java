package deribit.models.currencies;

import deribit.models.instruments.DeribitInstrumentFactory;

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
                .setName("Bitcoin")
                .setSymbol("BTC")
                .setBaseCurrency("BTC")
                .setQuoteCurrency("USD")

                // Default values: null
                .setFeePrecision(null)
                .setWithdrawalFee(null)
                .setMinimumConfirmations(null)
                .setMinimumWithdrawalFee(null)

                .build();
    }

    private static DeribitCurrency getEther(String s) {

        DeribitCurrency.Builder builder = new DeribitCurrency.Builder();
        return builder
                .setName("Ether")
                .setSymbol("ETH")
                .setBaseCurrency("ETH")
                .setQuoteCurrency("USD")

                // Default values: null
                .setFeePrecision(null)
                .setWithdrawalFee(null)
                .setMinimumConfirmations(null)
                .setMinimumWithdrawalFee(null)

                .build();
    }

}

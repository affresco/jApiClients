package deribit.models.instruments;


import commons.standards.InstrumentKind;
import deribit.models.currencies.DeribitCurrency;
import deribit.models.currencies.DeribitCurrencyFactory;
import deribit.models.expiries.DeribitExpiry;
import deribit.models.expiries.DeribitExpiryFactory;
import deribit.models.fees.DeribitFeeStructure;
import deribit.models.fees.DeribitFeeStructureFactory;
import deribit.models.market.DeribitMarketStructure;
import deribit.models.market.DeribitMarketStructureFactory;

import java.text.ParseException;

public class DeribitInstrumentFactory {

    private DeribitInstrumentFactory() {
    }

    public static DeribitInstrument getInstanceFromSymbol(String symbol) throws ParseException {

        // Make sure the format is right
        String s = symbol.toUpperCase();

        InstrumentKind kind = InstrumentKind.getKindForDeribit(s);

        if (kind == InstrumentKind.PERPETUAL) {
            return getPerpetual(s);
        }

        if (kind == InstrumentKind.FUTURE) {
            return getFuture(s);
        }

        if (kind == InstrumentKind.OPTION) {
            return getOption(s);
        }

        // Not sure what this is
        throw new IllegalArgumentException("Symbol could not be parsed into an instrument type: " + s);

    }

    public static DeribitFuture getFuture(String symbol) throws ParseException {

        // Build a currency object
        DeribitCurrency currency = DeribitCurrencyFactory.getInstanceFromSymbol(symbol.substring(0, 3));

        // Get a kind
        InstrumentKind kind = InstrumentKind.FUTURE;

        // Get an expiry structure
        DeribitExpiry expiry = DeribitExpiryFactory.getInstanceFromSymbol(symbol);

        // Get a fee structure
        DeribitFeeStructure feeStructure = DeribitFeeStructureFactory.getInstance(currency.getCryptoCurrency(), kind);

        // Get a market structure
        DeribitMarketStructure marketStructure = DeribitMarketStructureFactory.getInstance(currency.getCryptoCurrency(), kind);

        DeribitFuture.Builder builder = new DeribitFuture.Builder();

        return (DeribitFuture) builder
                .setSymbol(symbol)
                .setExpiry(expiry)
                .setFeeStructure(feeStructure)
                .setKind(kind)
                .setMarketStructure(marketStructure)
                .build();
    }

    public static DeribitOption getOption(String symbol) throws ParseException {

        // Build a currency object
        DeribitCurrency currency = DeribitCurrencyFactory.getInstanceFromSymbol(symbol.substring(0, 3));

        // Get a kind
        InstrumentKind kind = InstrumentKind.OPTION;

        // Get an expiry structure
        DeribitExpiry expiry = DeribitExpiryFactory.getInstanceFromSymbol(symbol);

        // Get a fee structure
        DeribitFeeStructure feeStructure = DeribitFeeStructureFactory.getInstance(currency.getCryptoCurrency(), kind);

        // Get a market structure
        DeribitMarketStructure marketStructure = DeribitMarketStructureFactory.getInstance(currency.getCryptoCurrency(), kind);

        DeribitFuture.Builder builder = new DeribitFuture.Builder();

        return (DeribitOption) builder
                .setSymbol(symbol)
                .setExpiry(expiry)
                .setFeeStructure(feeStructure)
                .setKind(kind)
                .setMarketStructure(marketStructure)
                .build();
    }


    public static DeribitPerpetual getPerpetual(String symbol) throws ParseException {

        // Build a currency object
        DeribitCurrency currency = DeribitCurrencyFactory.getInstanceFromSymbol(symbol.substring(0, 3));

        // Get a kind
        InstrumentKind kind = InstrumentKind.PERPETUAL;

        // Get an expiry structure
        DeribitExpiry expiry = DeribitExpiryFactory.getInstanceFromSymbol(symbol);

        // Get a fee structure
        DeribitFeeStructure feeStructure = DeribitFeeStructureFactory.getInstance(currency.getCryptoCurrency(), kind);

        // Get a market structure
        DeribitMarketStructure marketStructure = DeribitMarketStructureFactory.getInstance(currency.getCryptoCurrency(), kind);

        DeribitFuture.Builder builder = new DeribitFuture.Builder();

        return (DeribitPerpetual) builder
                .setSymbol(symbol)
                .setExpiry(expiry)
                .setFeeStructure(feeStructure)
                .setKind(kind)
                .setMarketStructure(marketStructure)
                .build();
    }


}

package deribit.models.instruments;


import commons.standards.Cryptocurrency;
import commons.standards.InstrumentKind;

import deribit.models.currencies.DeribitCurrency;
import deribit.models.currencies.DeribitCurrencyFactory;
import deribit.models.expiries.DeribitExpiry;
import deribit.models.expiries.DeribitExpiryFactory;
import deribit.models.fees.DeribitFeeStructure;
import deribit.models.fees.DeribitFeeStructureFactory;
import deribit.models.contract.DeribitContractStructure;
import deribit.models.contract.DeribitContractStructureFactory;

import java.text.ParseException;

public abstract class DeribitInstrumentFactory {

    private DeribitInstrumentFactory() {
    }

    public static DeribitInstrument getInstanceFromSymbol(String symbol) throws ParseException {

        // Make sure the format is right
        String s = symbol.toUpperCase();

        InstrumentKind kind = InstrumentKind.getKindForDeribit(s);

        if (kind == InstrumentKind.PERPETUAL) {
            return getPerpetual(s, kind);
        }

        if (kind == InstrumentKind.FUTURE) {
            return getFuture(s, kind);
        }

        if (kind == InstrumentKind.OPTION) {
            return getOption(s, kind);
        }

        if (kind == InstrumentKind.INDEX) {
            return getIndex(s, kind);
        }

        // Not sure what this is
        throw new IllegalArgumentException("Symbol could not be parsed into an instrument type: " + s);

    }

    public static DeribitPerpetual getPerpetual(String symbol, InstrumentKind kind) throws ParseException {

        // Build a currency object
        DeribitCurrency currency = DeribitCurrencyFactory.getInstanceFromSymbol(symbol.substring(0, 3));

        // Get an expiry structure
        DeribitExpiry expiry = DeribitExpiryFactory.getInstanceFromSymbol(symbol);

        // Get a fee structure
        Cryptocurrency c = currency.getCryptoCurrency();
        DeribitFeeStructure feeStructure = DeribitFeeStructureFactory.getInstance(c, kind);

        // Get a market structure
        DeribitContractStructure marketStructure = DeribitContractStructureFactory.getInstance(currency.getCryptoCurrency(), kind);

        DeribitPerpetual.Builder builder = new DeribitPerpetual.Builder();

        return (DeribitPerpetual) builder
                .setSymbol(symbol)
                .setExpiry(expiry)
                .setCurrency(currency)
                .setFeeStructure(feeStructure)
                .setKind(kind)
                .setContractStructure(marketStructure)
                .build();
    }

    public static DeribitFuture getFuture(String symbol, InstrumentKind kind) throws ParseException {

        // Build a currency object
        DeribitCurrency currency = DeribitCurrencyFactory.getInstanceFromSymbol(symbol.substring(0, 3));

        // Get an expiry structure
        DeribitExpiry expiry = DeribitExpiryFactory.getInstanceFromSymbol(symbol);

        // Get a fee structure
        DeribitFeeStructure feeStructure = DeribitFeeStructureFactory.getInstance(currency.getCryptoCurrency(), kind);

        // Get a market structure
        DeribitContractStructure marketStructure = DeribitContractStructureFactory.getInstance(currency.getCryptoCurrency(), kind);

        DeribitFuture.Builder builder = new DeribitFuture.Builder();

        return (DeribitFuture) builder
                .setSymbol(symbol)
                .setExpiry(expiry)
                .setCurrency(currency)
                .setFeeStructure(feeStructure)
                .setKind(kind)
                .setContractStructure(marketStructure)
                .build();
    }

    public static DeribitOption getOption(String symbol, InstrumentKind kind) throws ParseException {

        // Build a currency object
        DeribitCurrency currency = DeribitCurrencyFactory.getInstanceFromSymbol(symbol.substring(0, 3));

        // Get an expiry structure
        DeribitExpiry expiry = DeribitExpiryFactory.getInstanceFromSymbol(symbol);

        // Get a fee structure
        DeribitFeeStructure feeStructure = DeribitFeeStructureFactory.getInstance(currency.getCryptoCurrency(), kind);

        // Get a market structure
        DeribitContractStructure marketStructure = DeribitContractStructureFactory.getInstance(currency.getCryptoCurrency(), kind);

        DeribitFuture.Builder builder = new DeribitFuture.Builder();

        return (DeribitOption) builder
                .setSymbol(symbol)
                .setExpiry(expiry)
                .setCurrency(currency)
                .setFeeStructure(feeStructure)
                .setKind(kind)
                .setContractStructure(marketStructure)
                .build();
    }

    public static DeribitIndex getIndex(String symbol, InstrumentKind kind) {

        // Build a currency object
        DeribitCurrency currency = DeribitCurrencyFactory.getInstanceFromSymbol(symbol.substring(0, 3));

        DeribitIndex.Builder builder = new DeribitIndex.Builder();

        return (DeribitIndex) builder
                .setSymbol(symbol)
                .setCurrency(currency)
                .setKind(kind)
                .build();
    }

}

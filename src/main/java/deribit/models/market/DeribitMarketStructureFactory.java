package deribit.models.market;

import commons.standards.Cryptocurrency;
import commons.standards.InstrumentKind;
import deribit.models.currencies.DeribitCurrency;
import deribit.models.currencies.DeribitCurrencyFactory;

public class DeribitMarketStructureFactory {


    private DeribitMarketStructureFactory() {
    }

    public static DeribitMarketStructure getInstance(Cryptocurrency currency, InstrumentKind kind) {

        // This handles the Bitcoin
        if (currency == Cryptocurrency.BTC) {
            return DeribitMarketStructureFactory.getBitcoinMarketStructure(kind);
        }

        // This handles the Ether
        if (currency == Cryptocurrency.ETH) {
            return DeribitMarketStructureFactory.getEtherMarketStructure(kind);
        }

        throw new IllegalArgumentException("Currency is not supported");
    }

    private static DeribitMarketStructure getBitcoinMarketStructure(InstrumentKind kind) {
        if (kind == InstrumentKind.OPTION) {
            return getBitcoinMarketStructureForConvex();
        }
        if (kind == InstrumentKind.FUTURE || kind == InstrumentKind.PERPETUAL) {
            return getBitcoinMarketStructureForLinear();
        }

        throw new IllegalArgumentException("Kind is not supported in BTC");
    }

    private static DeribitMarketStructure getBitcoinMarketStructureForConvex() {
        DeribitMarketStructure.Builder builder = new DeribitMarketStructure.Builder();
        return builder
                .setContractSize(null)
                .setLeverage(null)
                .setTickSize(null)
                .setMinimumTradeAmount(null)
                .build();
    }

    private static DeribitMarketStructure getBitcoinMarketStructureForLinear() {
        DeribitMarketStructure.Builder builder = new DeribitMarketStructure.Builder();
        return builder
                .setContractSize(null)
                .setLeverage(null)
                .setTickSize(null)
                .setMinimumTradeAmount(null)
                .build();
    }

    private static DeribitMarketStructure getEtherMarketStructure(InstrumentKind kind) {
        if (kind == InstrumentKind.OPTION) {
            return getEtherMarketStructureForConvex();
        }
        if (kind == InstrumentKind.FUTURE || kind == InstrumentKind.PERPETUAL) {
            return getEtherMarketStructureForLinear();
        }

        throw new IllegalArgumentException("Kind is not supported in ETH");
    }

    private static DeribitMarketStructure getEtherMarketStructureForConvex() {
        DeribitMarketStructure.Builder builder = new DeribitMarketStructure.Builder();
        return builder
                .setContractSize(null)
                .setLeverage(null)
                .setTickSize(null)
                .setMinimumTradeAmount(null)
                .build();
    }

    private static DeribitMarketStructure getEtherMarketStructureForLinear() {
        DeribitMarketStructure.Builder builder = new DeribitMarketStructure.Builder();
        return builder
                .setContractSize(null)
                .setLeverage(null)
                .setTickSize(null)
                .setMinimumTradeAmount(null)
                .build();
    }


}
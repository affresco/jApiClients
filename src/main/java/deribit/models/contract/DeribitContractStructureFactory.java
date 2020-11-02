package deribit.models.contract;

import commons.standards.Cryptocurrency;
import commons.standards.InstrumentKind;

import deribit.models.contract.defaults.BitcoinFuturesContract;
import deribit.models.contract.defaults.BitcoinOptionContract;
import deribit.models.contract.defaults.EtherOptionContract;
import deribit.models.contract.defaults.EtherFuturesContract;

public class DeribitContractStructureFactory {


    private DeribitContractStructureFactory() {
    }

    public static DeribitContractStructure getInstance(Cryptocurrency currency, InstrumentKind kind) {

        // This handles the Bitcoin
        if (currency == Cryptocurrency.BTC) {
            return DeribitContractStructureFactory.getBitcoinMarketStructure(kind);
        }

        // This handles the Ether
        if (currency == Cryptocurrency.ETH) {
            return DeribitContractStructureFactory.getEtherMarketStructure(kind);
        }

        throw new IllegalArgumentException("Currency is not supported");
    }

    private static DeribitContractStructure getBitcoinMarketStructure(InstrumentKind kind) {
        if (kind == InstrumentKind.OPTION) {
            return getBitcoinMarketStructureForConvex();
        }
        if (kind == InstrumentKind.FUTURE || kind == InstrumentKind.PERPETUAL) {
            return getBitcoinMarketStructureForLinear();
        }

        throw new IllegalArgumentException("Kind is not supported in BTC");
    }

    private static DeribitContractStructure getBitcoinMarketStructureForConvex() {
        DeribitContractStructure.Builder builder = new DeribitContractStructure.Builder();
        return builder
                .setContractSize(BitcoinOptionContract.CONTRACT_SIZE)
                .setLeverage(BitcoinOptionContract.MAXIMUM_LEVERAGE)
                .setTickSize(BitcoinOptionContract.TICK_SIZE)
                .setMinimumTradeAmount(BitcoinOptionContract.MINIMUM_TRADED_AMOUNT)
                .build();
    }

    private static DeribitContractStructure getBitcoinMarketStructureForLinear() {
        DeribitContractStructure.Builder builder = new DeribitContractStructure.Builder();
        return builder
                .setContractSize(BitcoinFuturesContract.CONTRACT_SIZE)
                .setLeverage(BitcoinFuturesContract.MAXIMUM_LEVERAGE)
                .setTickSize(BitcoinFuturesContract.TICK_SIZE)
                .setMinimumTradeAmount(BitcoinFuturesContract.MINIMUM_TRADED_AMOUNT)
                .build();
    }

    private static DeribitContractStructure getEtherMarketStructure(InstrumentKind kind) {
        if (kind == InstrumentKind.OPTION) {
            return getEtherMarketStructureForConvex();
        }
        if (kind == InstrumentKind.FUTURE || kind == InstrumentKind.PERPETUAL) {
            return getEtherMarketStructureForLinear();
        }

        throw new IllegalArgumentException("Kind is not supported in ETH");
    }

    private static DeribitContractStructure getEtherMarketStructureForConvex() {
        DeribitContractStructure.Builder builder = new DeribitContractStructure.Builder();
        return builder
                .setContractSize(EtherOptionContract.CONTRACT_SIZE)
                .setLeverage(EtherOptionContract.MAXIMUM_LEVERAGE)
                .setTickSize(EtherOptionContract.TICK_SIZE)
                .setMinimumTradeAmount(EtherOptionContract.MINIMUM_TRADED_AMOUNT)
                .build();
    }

    private static DeribitContractStructure getEtherMarketStructureForLinear() {
        DeribitContractStructure.Builder builder = new DeribitContractStructure.Builder();
        return builder
                .setContractSize(EtherFuturesContract.CONTRACT_SIZE)
                .setLeverage(EtherFuturesContract.MAXIMUM_LEVERAGE)
                .setTickSize(EtherFuturesContract.TICK_SIZE)
                .setMinimumTradeAmount(EtherFuturesContract.MINIMUM_TRADED_AMOUNT)
                .build();
    }


}
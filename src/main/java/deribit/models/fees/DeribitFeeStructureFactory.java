package deribit.models.fees;

import commons.standards.Cryptocurrency;
import commons.standards.InstrumentKind;
import deribit.models.fees.defaults.BitcoinDefaultFees;
import deribit.models.fees.defaults.EtherDefaultFees;

public class DeribitFeeStructureFactory {


    private DeribitFeeStructureFactory() {
    }

    public static DeribitFeeStructure getInstance(Cryptocurrency currency, InstrumentKind kind) {

        // This handles the Bitcoin
        if (currency == Cryptocurrency.BTC) {
            return DeribitFeeStructureFactory.getBitcoinFeeStructure(kind);
        }

        // This handles the Ether
        if (currency == Cryptocurrency.ETH) {
            return DeribitFeeStructureFactory.getEtherFeeStructure(kind);
        }

        throw new IllegalArgumentException("Currency is not supported");
    }

    private static DeribitFeeStructure getBitcoinFeeStructure(InstrumentKind kind) {
        if (kind == InstrumentKind.OPTION) {
            return getBitcoinFeeStructureForConvex();
        }
        if (kind == InstrumentKind.FUTURE || kind == InstrumentKind.PERPETUAL) {
            return getBitcoinFeeStructureForLinear();
        }

        throw new IllegalArgumentException("Kind is not supported in BTC");
    }

    private static DeribitFeeStructure getBitcoinFeeStructureForConvex() {
        DeribitFeeStructure.Builder builder = new DeribitFeeStructure.Builder();
        return builder
                .setLiquidityTaker(BitcoinDefaultFees.OPTION_TAKER_FROM_UNDERLYING)
                .setLiquidityMaker(BitcoinDefaultFees.OPTION_MAKER_FROM_UNDERLYING)
                .build();
    }

    private static DeribitFeeStructure getBitcoinFeeStructureForLinear() {
        DeribitFeeStructure.Builder builder = new DeribitFeeStructure.Builder();
        return builder
                .setLiquidityTaker(BitcoinDefaultFees.FUTURE_TAKER)
                .setLiquidityMaker(BitcoinDefaultFees.FUTURE_MAKER)
                .build();
    }

    private static DeribitFeeStructure getEtherFeeStructure(InstrumentKind kind) {
        if (kind == InstrumentKind.OPTION) {
            return getEtherFeeStructureForConvex();
        }
        if (kind == InstrumentKind.FUTURE || kind == InstrumentKind.PERPETUAL) {
            return getEtherFeeStructureForLinear();
        }

        throw new IllegalArgumentException("Kind is not supported in ETH");
    }

    private static DeribitFeeStructure getEtherFeeStructureForConvex() {
        DeribitFeeStructure.Builder builder = new DeribitFeeStructure.Builder();
        return builder
                .setLiquidityTaker(EtherDefaultFees.OPTION_TAKER_FROM_UNDERLYING)
                .setLiquidityMaker(EtherDefaultFees.OPTION_MAKER_FROM_UNDERLYING)
                .build();
    }

    private static DeribitFeeStructure getEtherFeeStructureForLinear() {
        DeribitFeeStructure.Builder builder = new DeribitFeeStructure.Builder();
        return builder
                .setLiquidityTaker(EtherDefaultFees.FUTURE_MAKER)
                .setLiquidityMaker(EtherDefaultFees.FUTURE_MAKER)
                .build();
    }


}
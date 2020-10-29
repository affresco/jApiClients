package deribit.models.positions;

import commons.standards.InstrumentKind;
import deribit.models.instruments.DeribitInstrument;
import deribit.models.instruments.DeribitInstrumentFactory;

import java.text.ParseException;

public class DeribitPositionFactory {

    public static DeribitPosition getInstance(DeribitPositionMessage message) throws ParseException {

        // Instrument instance from factory
        DeribitInstrument ins = DeribitInstrumentFactory.getInstanceFromSymbol(message.getInstrument());

        // Kind enum
        InstrumentKind kind = InstrumentKind.FUTURE;

        DeribitPosition.Builder positionBuilder = new DeribitPosition.Builder();
        return positionBuilder
                .setTotalProfitLoss(message.getTotalProfitLoss())
                .setSizeCurrency(message.getSizeCurrency())
                .setSize(message.getSize())
                .setSettlementPrice(message.getSettlementPrice())
                .setRealizedProfitLoss(message.getRealizedProfitLoss())
                .setRealizedFunding(message.getRealizedFunding())
                .setOpenOrdersMargin(message.getOpenOrdersMargin())
                .setMarkPrice(message.getMarkPrice())
                .setMaintenanceMargin(message.getMaintenanceMargin())
                .setLeverage(message.getLeverage())

                // Kind is special
                .setKind(kind)

                // Instrument is special
                .setInstrument(ins)

                // Direction is special
                .setDirection(message.getDirection())

                .setInitialMargin(message.getInitialMargin())
                .setIndexPrice(message.getIndexPrice())
                .setFloatingProfitLoss(message.getFloatingProfitLoss())
                .setEstimatedLiquidationPrice(message.getEstimatedLiquidationPrice())
                .setDelta(message.getDelta())
                .setAveragePrice(message.getAveragePrice())

                // Finally, build it
                .build();
    }

}


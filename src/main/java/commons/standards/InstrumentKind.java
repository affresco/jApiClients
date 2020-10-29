package commons.standards;

import deribit.models.instruments.DeribitInstrumentFactory;

public enum InstrumentKind {
    FUTURE,
    OPTION,
    PERPETUAL,
    SPOT;

    public static InstrumentKind getKindForDeribit(String instrument) {

        instrument = instrument.toUpperCase();

        // This handles the perpetual
        if (instrument.contains("PERPETUAL")) {
            return InstrumentKind.PERPETUAL;
        }

        // Split the string
        int length = instrument.split("-").length;

        // This is an option, e.g. BTC-31OCT20-11000-P
        if (length == 4) {
            return InstrumentKind.OPTION;
        }

        // This is an future, e.g. BTC-25DEC20
        if (length == 2) {
            return InstrumentKind.FUTURE;
        }

        // Not sure what this is
        throw new IllegalArgumentException("Symbol could not be parsed into an instrument type: " + instrument);

    }

}

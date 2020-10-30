package deribit.models.expiries;

import commons.standards.InstrumentKind;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Instant;

public class DeribitExpiryFactory {

    private DeribitExpiryFactory() {
    }

    public static DeribitExpiry getInstanceFromSymbol(String symbol) throws ParseException {

        InstrumentKind kind = InstrumentKind.getKindForDeribit(symbol);

        if (kind == InstrumentKind.PERPETUAL) {
            return getPerpetual();
        }
        else{
            return getTermedExpiry(symbol);
        }
    }

    private static DeribitExpiry getPerpetual() {

        Instant creationInstant = Instant.now();
        Instant expiryInstant = Instant.MAX;

        DeribitExpiry.Builder builder = new DeribitExpiry.Builder();
        return builder
                .setCreationDate(creationInstant)
                .setExpiryDate(expiryInstant)
                .setSettlementPeriod(null)
                .build();
    }

    private static DeribitExpiry getTermedExpiry(String symbol) throws ParseException {

        String sDate = symbol.split("-")[1];
        Date expiryDate = new SimpleDateFormat("dd-MMM-yy").parse(sDate);
        Instant expiryInstant = expiryDate.toInstant();

        Instant creationInstant = Instant.now();

        DeribitExpiry.Builder builder = new DeribitExpiry.Builder();
        return builder
                .setCreationDate(creationInstant)
                .setExpiryDate(expiryInstant)
                .setSettlementPeriod(null)
                .build();
    }

}

package deribit.models.expiries;

import commons.standards.InstrumentKind;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        Date creationDate = new Date(0);
        Date expiryDate = new Date(Long.MAX_VALUE);

        DeribitExpiry.Builder builder = new DeribitExpiry.Builder();
        return builder
                .setCreationDate(creationDate)
                .setExpiryDate(expiryDate)
                .setSettlementPeriod(null)
                .build();
    }

    private static DeribitExpiry getTermedExpiry(String symbol) throws ParseException {

        String sDate = symbol.split("-")[1];
        Date expiryDate = new SimpleDateFormat("dd-MMM-yy").parse(sDate);
        Date creationDate = new Date(0);

        DeribitExpiry.Builder builder = new DeribitExpiry.Builder();
        return builder
                .setCreationDate(creationDate)
                .setExpiryDate(expiryDate)
                .setSettlementPeriod(null)
                .build();
    }

}

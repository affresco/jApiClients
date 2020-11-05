package apps.market.models.quotes;

import apps.market.exceptions.InvalidMessageException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import commons.standards.Cryptocurrency;
import deribit.models.currencies.DeribitCurrency;
import deribit.models.instruments.DeribitInstrument;
import deribit.models.instruments.DeribitInstrumentFactory;

import java.text.ParseException;
import java.util.LinkedHashMap;

public class DeribitIndexQuoteMessage extends DeribitQuoteMessage {

    @JsonProperty("params")
    public void setParams(LinkedHashMap<String, Object> value) throws InvalidMessageException {

        // Cast works, this info is definitely of type 'String'
        String instrumentSymbol = ((String) value.get("channel")).split("\\.")[1];
        DeribitInstrument instrument;
        Cryptocurrency currency;
        try {
            instrument = DeribitInstrumentFactory.getInstanceFromSymbol(instrumentSymbol);
            currency = instrument.getCurrency().getCryptoCurrency();
        } catch (ParseException e) {
            throw new InvalidMessageException();
        }

        // Cast should work...
        LinkedHashMap<Object, Object> data = (LinkedHashMap<Object, Object>) value.get("data");

        DeribitQuote.Builder quoteBuilder = new DeribitQuote.Builder();

        Double price = (Double) data.get("price");

        // Timestamp has a special treatment
        double timestamp = ((Long) data.get("timestamp")).doubleValue();
        timestamp = timestamp / 1000.0;

        this.quote = quoteBuilder
                .setBidPrice(price)
                .setAskPrice(price)
                .setTimestamp(timestamp)
                .setInstrument(instrument)
                .setCurrency(currency)
                .build();

    }


}
package apps.market.models.quotes;

import apps.market.exceptions.InvalidMessageException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import commons.standards.Cryptocurrency;
import deribit.models.instruments.DeribitInstrument;
import deribit.models.instruments.DeribitInstrumentFactory;

import java.security.DomainLoadStoreParameter;
import java.text.ParseException;
import java.util.LinkedHashMap;

public class DeribitQuoteMessage {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    @JsonIgnore
    private String jsonrpc = "2.0";
    @JsonIgnore
    private String method = "subscription";
    @JsonIgnore
    protected DeribitQuote quote;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public DeribitQuoteMessage() {
    }

    public DeribitQuote getQuote() {
        return quote;
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public String getJsonrpc() {
        return new String(jsonrpc);
    }

    public String getMethod() {
        return new String(method);
    }

    // ##################################################################
    // SETTERS -- LOADING WITH JACKSON DATABIND
    // ##################################################################

    @JsonIgnore
    public void setJsonrpc(String value) {
        this.jsonrpc = value;
    }

    @JsonIgnore
    public void setMethod(String value) {
        this.method = value;
    }


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

        Double bidPrice = (Double) data.get("best_bid_price");
        Double askPrice = (Double) data.get("best_ask_price");
        Double bidQuantity = (Double) data.get("best_bid_amount");
        Double askQuantity = (Double) data.get("best_ask_amount");

        // Timestamp has a special treatment
        double timestamp = ((Long) data.get("timestamp")).doubleValue();
        timestamp = timestamp / 1000.0;

        this.quote = quoteBuilder
                .setBidPrice(bidPrice)
                .setAskPrice(askPrice)
                .setBidQuantity(bidQuantity)
                .setAskQuantity(askQuantity)
                .setTimestamp(timestamp)
                .setInstrument(instrument)
                .setCurrency(currency)
                .build();

    }



}



































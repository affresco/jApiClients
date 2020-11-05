package commons.models.quotes;

import commons.models.exchange.DerivativeExchange;
import commons.models.instruments.BaseInstrument;

public interface Quote {

    BaseInstrument getInstrument();

    double getBidPrice();
    double getAskPrice();

    double getBidQuantity();
    double getAskQuantity();

    double getTimestamp();
    DerivativeExchange getExchange();

    boolean isValid();

}
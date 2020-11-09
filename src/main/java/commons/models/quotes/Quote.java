package commons.models.quotes;

import commons.standards.DerivativeExchange;
import commons.models.instruments.BaseInstrument;

public interface Quote {

    BaseInstrument getInstrument();
    DerivativeExchange getExchange();

    Double getBidPrice();
    Double getAskPrice();

    Double getBidQuantity();
    Double getAskQuantity();

    Double getTimestamp();

    // Validation from inside
    boolean isValid();

}

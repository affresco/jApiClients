package apps.execution.models.orders;

import apps.execution.models.atomic.DeribitAtomicOrder;
import commons.standards.QuoteDirection;
import commons.standards.TimeInForce;
import deribit.models.instruments.DeribitInstrument;

import java.util.HashMap;

public class DeribitSpreadMarketOrder extends DeribitOrder {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private HashMap<Integer, DeribitAtomicOrder> executionSchedule;

    // Total (maximum) duration of this order
    private final int durationInSeconds = 120;

    // Time step (i.e. minimum spacing) in between consecutive orders
    private final int minimumTimeSpacingInSeconds = 1;

    // Minimum trade size allowed on the exchange
    private double minimumTradeAmount = 10.0;

    // ##################################################################
    // CONSTRUCTOR
    // ##################################################################

    public DeribitSpreadMarketOrder(DeribitInstrument instrument, double amount, QuoteDirection direction, TimeInForce timeInForce, String label) {
        super(instrument, amount, direction, timeInForce, label);
        minimumTradeAmount = instrument.getContractStructure().getMinimumTradeAmount();
    }

    // ##################################################################
    // IMPLEMENTATIONS
    // ##################################################################

    @Override
    public HashMap<Integer, DeribitAtomicOrder> getExecutionSchedule() {
        if(executionSchedule == null){
            setExecutionSchedule();
        }
        return executionSchedule;
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public double getMinimumTradeAmount() {
        return minimumTradeAmount;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public int getMinimumTimeSpacingInSeconds() {
        return minimumTimeSpacingInSeconds;
    }

    // ##################################################################
    // CORE
    // ##################################################################

    private void setExecutionSchedule(){
        if(executionSchedule == null){
            setExecutionSchedule();
        }
        executionSchedule = buildSchedule();
    }

    private HashMap<Integer, DeribitAtomicOrder> buildSchedule(){
        return null;
    }

}

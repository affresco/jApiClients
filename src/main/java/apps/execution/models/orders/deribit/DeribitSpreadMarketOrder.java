package apps.execution.models.orders.deribit;

import apps.execution.models.atomic.deribit.DeribitAtomicMarketOrder;
import apps.execution.models.support.AtomicOrderSchedule;
import apps.execution.models.support.AtomicOrdersScheduleBuilder;
import apps.execution.models.support.DeribitAtomicOrderSchedule;
import commons.standards.QuoteDirection;
import commons.standards.TimeInForce;
import deribit.models.instruments.DeribitInstrument;

import java.util.HashMap;

public class DeribitSpreadMarketOrder extends DeribitOrder {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private DeribitAtomicOrderSchedule executionSchedule;

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
    public AtomicOrderSchedule getExecutionSchedule() {
        if(executionSchedule == null){
            try {
                setExecutionSchedule();
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    private void setExecutionSchedule() throws Exception {
        if(executionSchedule == null){
            setExecutionSchedule();
        }
        executionSchedule = buildSchedule();
    }

    private DeribitAtomicOrderSchedule buildSchedule() throws Exception {

        // Get a temporary schedule with the amounts
        HashMap<Integer, Double> amountSchedule = AtomicOrdersScheduleBuilder.buildSchedule(getAmount(),
                getMinimumTradeAmount(), durationInSeconds, minimumTimeSpacingInSeconds);

        DeribitAtomicOrderSchedule schedule = new DeribitAtomicOrderSchedule();
        int k = 0;

        for(int t: amountSchedule.keySet()) {
            String subLabel = getLabel() + "-" + k;
            schedule.add(t, new DeribitAtomicMarketOrder(getInstrument(), amountSchedule.get(t), getDirection(), subLabel));
        }
        return schedule;

    }

}













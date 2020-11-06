package apps.execution.models.support;

import apps.execution.models.atomic.DeribitAtomicOrder;

import java.util.HashMap;

public class DeribitAtomicOrderSchedule {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private final HashMap<Integer, DeribitAtomicOrder> executionSchedule;

    // ##################################################################
    // CONSTRUCTOR
    // ##################################################################

    public DeribitAtomicOrderSchedule() {
        executionSchedule = new HashMap<>();
    }

    // ##################################################################
    // INTERFACE
    // ##################################################################

    public void add(int delayInMilliseconds, DeribitAtomicOrder order){

        if(executionSchedule.containsKey(delayInMilliseconds)){
            throw new IllegalArgumentException();
        }

        executionSchedule.put(delayInMilliseconds, order);
    }

}

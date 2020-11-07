package apps.execution.models.support;

import apps.execution.models.atomic.AtomicOrder;
import apps.execution.models.atomic.deribit.DeribitAtomicOrder;

import java.util.HashMap;

public class DeribitAtomicOrderSchedule implements AtomicOrderSchedule {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private final HashMap<Integer, DeribitAtomicOrder> schedule;

    // ##################################################################
    // CONSTRUCTOR
    // ##################################################################

    public DeribitAtomicOrderSchedule() {
        schedule = new HashMap<>();
    }

    // ##################################################################
    // INTERFACE
    // ##################################################################

    public void add(int delayInMilliseconds, DeribitAtomicOrder order){

        if(schedule.containsKey(delayInMilliseconds)){
            throw new IllegalArgumentException();
        }

        schedule.put(delayInMilliseconds, order);
    }

    public HashMap<Integer, AtomicOrder> getSchedule() {
        HashMap<Integer, AtomicOrder> output = new HashMap<>();
        for(int t: schedule.keySet()){
            output.put(t, schedule.get(t));
        }
        return output;
    }
}

package apps.execution.services;

import apps.execution.models.atomic.AtomicOrder;
import apps.execution.models.orders.Order;
import apps.execution.models.support.AtomicOrderSchedule;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

class ExecutionService {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    // Flag to indicate if active
    private boolean isHalted = false;

    // Execution schedule: keyed by time of execution,
    // values are list of atomic orders waiting for execution at that time
    private HashMap<Instant, ArrayList<AtomicOrder>> executionSchedule;

    // ##################################################################
    // CONSTRUCTOR
    // ##################################################################

    ExecutionService(){}

    // ##################################################################
    // METHODS MODIFYING THE STATE OF THIS OBJECT
    // ##################################################################

    void halt(){
        synchronized (ExecutionService.class) {
            this.isHalted = true;
        }
    }

    void resume(){
        synchronized (ExecutionService.class) {
            this.isHalted = false;
        }
    }

    // ##################################################################
    // METHODS
    // ##################################################################

    void scheduleExecution(Order order){
        AtomicOrderSchedule schedule = order.getExecutionSchedule();
        var orderSchedule = schedule.getSchedule();
        for(int delayInMilliseconds : orderSchedule.keySet()){

            Instant executionTime = Instant.now().plusMillis(delayInMilliseconds);
            ArrayList<AtomicOrder> atomicOrders;

            // Orders already registered for this instant
            if (executionSchedule.containsKey(executionTime)){
                atomicOrders = executionSchedule.get(executionTime);
            }

            // Nothing happening at this instant (yet)
            else{
                atomicOrders = new ArrayList<>();
            }

            // Todo: in the python implementation, the orders are not stored directly
            //  but there are orderRecords created...
            atomicOrders.add(orderSchedule.get(delayInMilliseconds));
            executionSchedule.put(executionTime, atomicOrders);
        }
    }

}

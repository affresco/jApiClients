package apps.execution.models.orders;

import apps.execution.models.support.AtomicOrderSchedule;

public interface Order {

    // ##################################################################
    // ABSTRACT METHODS
    // ##################################################################

    AtomicOrderSchedule getExecutionSchedule();


}

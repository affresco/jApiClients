package apps.execution.models.support;

import apps.execution.models.atomic.AtomicOrder;

import java.util.HashMap;

public interface AtomicOrderSchedule {

    HashMap<Integer, AtomicOrder> getSchedule();

}

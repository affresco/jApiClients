package apps.execution.events;

import apps.execution.models.orders.Order;

public class ExecutionRequestEvent {

    private final Order order;

    public ExecutionRequestEvent(Order order){
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}

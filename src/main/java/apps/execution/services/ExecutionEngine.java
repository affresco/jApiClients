package apps.execution.services;


import apps.execution.events.ExecutionRequestEvent;
import apps.execution.events.HaltExecutionRequestEvent;
import apps.execution.events.ResumeExecutionRequestEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ExecutionEngine {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    // Flag to indicate if active
    private boolean isHalted = false;
    private boolean isReady = false;

    // Services
    private final ExecutionService executionService;
    private final MonitoringService monitoringService;

    // ##################################################################
    // CONSTRUCTOR
    // ##################################################################

    private ExecutionEngine() {
        /*
        The purpose of this class is to centralize and maintain the underlying services:
            >> execution (handles execution)
            >> monitoring (checks for concurrent agents)
            >> etc.
         */

        // Instantiate the underlying services
        this.executionService = new ExecutionService();
        this.monitoringService = new MonitoringService();

        // Subscribe to events
        EventBus.getDefault().register(this);
    }

    // ##################################################################
    // EVENT HANDLERS: HALT / RESUME EXECUTION
    // ##################################################################

    @Subscribe
    public void onHalt(HaltExecutionRequestEvent event) {
        synchronized (ExecutionEngine.class) {
            isHalted = true;
            executionService.halt();
        }
    }

    @Subscribe
    public void onResume(ResumeExecutionRequestEvent event) {
        synchronized (ExecutionEngine.class) {
            isHalted = false;
            executionService.resume();
        }
    }

    // ##################################################################
    // EVENT HANDLERS: EXECUTION REQUESTS
    // ##################################################################

    @Subscribe
    public void onExecutionRequest(ExecutionRequestEvent event) {
        executionService.scheduleExecution(event.getOrder());
    }

    // ##################################################################
    // METHODS
    // ##################################################################

    public boolean isHalted() {
        return isHalted;
    }

    public boolean isReady() {
        return isReady;
    }


}

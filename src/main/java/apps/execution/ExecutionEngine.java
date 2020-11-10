package apps.execution;


import apps.execution.events.ExecutionRequestEvent;
import apps.execution.events.HaltExecutionRequestEvent;
import apps.execution.events.ResumeExecutionRequestEvent;
import apps.execution.services.ExecutionService;
import apps.execution.services.MonitoringService;
import apps.market.Settings;
import org.apache.log4j.BasicConfigurator;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    // Logging via SLF4J
    private static Logger logger;

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

        // Setup our logger
        setLogger();

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

    // ##################################################################
    // LOGGING
    // ##################################################################

    protected static void setLogger() {
        if (logger == null) {
            logger = LoggerFactory.getLogger(Settings.SYMBOL.toString());
            BasicConfigurator.configure();
        }
    }


}

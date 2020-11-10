package apps.system;

import apps.calibration.Settings;
import org.apache.log4j.BasicConfigurator;
import org.greenrobot.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public abstract class AppEngine {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    // Logging via SLF4J
    protected static Logger logger;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public AppEngine(String loggerName) {

        // Set logger
        setLogger(loggerName);

        // Register to the event bus
        EventBus.getDefault().register(this);
    }

    // ##################################################################
    // LOGGING
    // ##################################################################

    protected static Logger getLogger() {
        return logger;
    }

    protected static void setLogger(String loggerName) {
        if (logger == null) {
            logger = LoggerFactory.getLogger(loggerName);
            BasicConfigurator.configure();
        }
    }

    // ##################################################################
    // SYSTEM EVENTS HANDLERS
    // ##################################################################



}

package apps.market.logging;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarketAppLogger {

    private static final String PREFIX = "[MKT] ";

    private static Logger logger;

    protected static Logger getLogger() {
        if(logger == null){
            logger = LoggerFactory.getLogger(MarketAppLogger.class);
            //BasicConfigurator.configure();
        }
        return logger;
    }

    public static void debug(String message) {
        Logger l = getLogger();
        l.debug(PREFIX + message);
    }
    public static void debug(String message, Exception e) {
        Logger l = getLogger();
        l.debug(PREFIX + message + ": " + e.getMessage());
    }
    public static void info(String message) {
        Logger l = getLogger();
        l.info(PREFIX + message);
    }
    public static void info(String message, Exception e) {
        Logger l = getLogger();
        l.info(PREFIX + message + ": " + e.getMessage());
    }
    public static void warn(String message) {
        Logger l = getLogger();
        l.warn(PREFIX + message);
    }
    public static void warn(String message, Exception e) {
        Logger l = getLogger();
        l.warn(PREFIX + message + ": " + e.getMessage());
    }
    public static void trace(String message) {
        Logger l = getLogger();
        l.trace(PREFIX + message);
    }
    public static void trace(String message, Exception e) {
        Logger l = getLogger();
        l.trace(PREFIX + message + ": " + e.getMessage());
    }
    public static void error(String message) {
        Logger l = getLogger();
        l.error(PREFIX + message);
    }
    public static void error(String message, Exception e) {
        Logger l = getLogger();
        l.error(PREFIX + message + ": " + e.getMessage());
    }

}

package apps.calibration;

import apps.calibration.events.VolatilitySmileCalibrationRequestEvent;
import apps.positions.events.ExchangePositionOpenedEvent;
import commons.models.expiries.Expiry;
import org.apache.log4j.BasicConfigurator;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


public class CalibrationEngine {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    // Logging via SLF4J
    private static Logger logger;

    // Calibrations are provided
    // for these registered expiries
    private final ArrayList<Expiry> expiries;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public CalibrationEngine() {
        expiries = new ArrayList<>();
        EventBus.getDefault().register(this);
    }

    // ##################################################################
    // LOGGING
    // ##################################################################

    protected static Logger getLogger() {
        if (logger == null) {
            logger = LoggerFactory.getLogger(Settings.SYMBOL.toString());
            BasicConfigurator.configure();
        }
        return logger;
    }

    // ##################################################################
    // EVENT HANDLERS: VOLATILITY SMILE CALIBRATION REQUESTS
    // ##################################################################

    @Subscribe
    public void onVolatilitySmileCalibrationRequest(VolatilitySmileCalibrationRequestEvent event) {
        Expiry requestedExpiry = event.getExpiry();
        if (!expiries.contains(requestedExpiry)){
            expiries.add(requestedExpiry);
        }
    }

    // ##################################################################
    // EVENT HANDLERS: EXCHANGE POSITIONS CREATION
    // ##################################################################

    @Subscribe
    public void onExchangePositionOpened(ExchangePositionOpenedEvent event) {
        // Get the expiry from the instrument and add it to the list
    }

    // ##################################################################
    // LOGGING
    // ##################################################################


}

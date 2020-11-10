package apps.calibration;

import apps.calibration.events.VolatilitySmileCalibrationRequestEvent;
import apps.calibration.exceptions.ExchangeNotSupportedException;
import apps.calibration.helpers.SviCalibrationHelper;
import apps.calibration.helpers.SviCalibrationHelperFactory;
import apps.positions.events.ExchangePositionClosedEvent;
import apps.positions.events.ExchangePositionOpenedEvent;
import apps.system.AppEngine;
import commons.models.expiries.Expiry;
import commons.standards.Cryptocurrency;
import commons.standards.DerivativeExchange;
import org.apache.log4j.BasicConfigurator;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class CalibrationEngine extends AppEngine {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    // Logging via SLF4J
    private static Logger logger;

    // Calibrations are provided
    // for these registered expiries
    private final ArrayList<SviCalibrationHelper> helpers;

    // Periodic refresh thread pool
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public CalibrationEngine() {

        // Init for basic events and logger
        super(Settings.SYMBOL.toString());

        // Initialize the container
        helpers = new ArrayList<>();

        // TODO: This is done in super() now...
        // EventBus.getDefault().register(this);

        logger.info("Calibration engine instantiated.");
    }

    // ##################################################################
    // EVENT HANDLERS: VOLATILITY SMILE CALIBRATION REQUESTS
    // ##################################################################

    @Subscribe
    public void onVolatilitySmileCalibrationRequest(VolatilitySmileCalibrationRequestEvent event) {
        try {
            registerExpiry(event.getExchange(), event.getCurrency(), event.getExpiry());
        }
        catch (ExchangeNotSupportedException e) {
            logger.error("Event handler failed to register a smile calibration helper.");
        }
    }

    // ##################################################################
    // EVENT HANDLERS: EXCHANGE POSITIONS CREATION
    // ##################################################################

    @Subscribe
    public void onExchangePositionOpened(ExchangePositionOpenedEvent event) {
        // Get the expiry from the instrument and add it to the list
    }

    @Subscribe
    public void onExchangePositionClosed(ExchangePositionClosedEvent event) {
        // Get the expiry from the instrument and remove it to the list if the
        // it's the only observer... tbd
    }

    // ##################################################################
    // ADDING/REMOVING CALIBRATION HELPERS
    // ##################################################################

    protected void registerExpiry(DerivativeExchange exchange, Cryptocurrency currency, Expiry expiry) throws ExchangeNotSupportedException {

        SviCalibrationHelper helper = SviCalibrationHelperFactory.getInstance(exchange, currency, expiry);

        if (!helpers.contains(helper)){

            // Add it to the dict
            helpers.add(helper);

            // Schedule with random period (avoids congestion on micro-service)
            scheduler.scheduleWithFixedDelay(helper, 0, getRandomPeriod(), TimeUnit.SECONDS);
        }
    }

    protected void unregisterExpiry(){
        // pass for now
    }

    // ##################################################################
    // SUPPORT
    // ##################################################################

    private int getRandomPeriod(){
        int width = 5;
        int center = 60;
        Random random = new Random();
        return center - width + random.nextInt(2 * width);
    }

    // ##################################################################
    // ADDING/REMOVING CALIBRATION HELPERS
    // ##################################################################




}















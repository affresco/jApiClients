package apps.calibration.helpers;

import apps.calibration.exceptions.SviCalibrationRefreshFailureException;
import apps.calibration.models.SviCalibration;
import apps.calibration.clients.SviCalibrationClient;
import apps.calibration.models.VolatilitySmileFactory;
import apps.market.Settings;
import clients.http.BaseHttpResponse;
import commons.models.data.VolatilitySmile;
import commons.models.expiries.Expiry;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Objects;

public class SviCalibrationHelper implements Runnable {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    // The current calibration
    private SviCalibration calibration;

    // The expiry targeted
    private final Expiry expiry;
    private final String expiryString;

    // The client used to recover/fetch the calibration
    private final SviCalibrationClient client;

    // The last time we have made a successful refresh
    private Instant lastRefresh;

    // ...and count the number of failed attempts
    private int failedRefreshAttempts = 0;

    // Logging via SLF4J
    private static Logger logger;

    // Refresh interval : Default (static)
    private final static int DEFAULT_REFRESH_INTERVAL = 60000;

    // Number of failed attempts allowed before declaring failure
    private final static int MAXIMUM_FAILED_ATTEMPTS = 5;

    // Current settings
    private int refreshInterval;

    // ##################################################################
    // CONSTRUCTOR
    // ##################################################################

    public SviCalibrationHelper(Expiry expiry, SviCalibrationClient client) {
        this(expiry, client, DEFAULT_REFRESH_INTERVAL);
    }

    public SviCalibrationHelper(Expiry expiry, SviCalibrationClient client, int refreshInterval) {

        // Sanity checks
        Objects.requireNonNull(expiry, "Expiry provided cannot be null.");
        Objects.requireNonNull(client, "Client provided cannot be null.");

        // Set references
        this.expiry = expiry;
        this.client = client;

        // Set the expiry string
        this.expiryString = expiry.getExpiryDateAsString();

        // Setup our logger
        setLogger();
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public Expiry getExpiry() {
        return expiry;
    }

    public SviCalibration getCalibration() {
        return calibration;
    }

    // ##################################################################
    // CORE METHODS
    // ##################################################################

    public void refresh() throws SviCalibrationRefreshFailureException {

        // Query our micro-service
        BaseHttpResponse response = client.fetch(this.expiryString);

        // Failed to get a response
        if (Objects.isNull(response) || response.getStatusCode() != 200) {
            onFailedRefresh();
        } else {
            VolatilitySmile sviCalibration = VolatilitySmileFactory.parseHttpResponse(response);
            calibration.update(sviCalibration);
        }
    }

    // ##################################################################
    // KEEPING TRACK OF SUCCESS
    // ##################################################################

    private void onFailedRefresh() throws SviCalibrationRefreshFailureException {
        failedRefreshAttempts += 1;

        if(failedRefreshAttempts >= MAXIMUM_FAILED_ATTEMPTS){
            throw new SviCalibrationRefreshFailureException("Maximum number of failed attempts reached.");
        }
    }

    private void onSuccessfulRefresh() {

        // Mark this refresh as OK
        lastRefresh = Instant.now();

        // And reset our counter
        failedRefreshAttempts = 0;
    }

    // ##################################################################
    // UPDATE CALIBRATION
    // ##################################################################

    private void updateCalibration(VolatilitySmile sviCalibration) {
        try {
            calibration.update(sviCalibration);
        } catch (Exception e) {
            logger.error("Failed to update calibration");
        }

        // Proceed to flag this as successful refresh
        onSuccessfulRefresh();
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

    // ##################################################################
    // RUNNABLE INTERFACE
    // ##################################################################

    @Override
    public void run() {
        try {
            refresh();
        }

        catch (SviCalibrationRefreshFailureException e) {
            logger.error("Failed periodic refresh: maximum number of attempts reached.");
        }

        catch (Exception e) {
            logger.error("Failed periodic refresh: unknown cause.");
        }
    }

}


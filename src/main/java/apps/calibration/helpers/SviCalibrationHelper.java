package apps.calibration.helpers;

import apps.calibration.exceptions.SviCalibrationRefreshFailureException;
import apps.calibration.models.SviCalibration;
import apps.calibration.clients.SviCalibrationClient;
import apps.calibration.models.VolatilitySmileFactory;
import apps.connectivity.ApiServiceProvider;
import apps.connectivity.events.ConnectivityLossEvent;
import apps.market.Settings;
import clients.http.BaseHttpResponse;
import commons.models.data.VolatilitySmile;
import commons.models.expiries.Expiry;
import commons.standards.Cryptocurrency;
import org.apache.log4j.BasicConfigurator;
import org.greenrobot.eventbus.EventBus;
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

    // The currency targeted
    private final Cryptocurrency currency;

    // The expiry targeted
    private final Expiry expiry;

    // The client used to recover/fetch the calibration
    private final SviCalibrationClient client;

    // The last time we have made a successful refresh
    private Instant lastRefresh;

    // ...and count the number of failed attempts
    private int failedRefreshAttempts = 0;

    // Logging via SLF4J
    private static Logger logger;

    // Number of failed attempts allowed before declaring failure
    private final static int MAXIMUM_FAILED_ATTEMPTS = 5;

    // ##################################################################
    // CONSTRUCTOR
    // ##################################################################

    public SviCalibrationHelper(Cryptocurrency currency, Expiry expiry, SviCalibrationClient client) {

        // Sanity checks
        Objects.requireNonNull(expiry, "Expiry provided cannot be null.");
        Objects.requireNonNull(client, "Client provided cannot be null.");
        Objects.requireNonNull(currency, "Currency provided cannot be null.");

        // Set references
        this.expiry = expiry;
        this.currency = currency;
        this.client = client;

        // Setup our logger
        setLogger();
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public Expiry getExpiry() {
        return expiry;
    }

    public Cryptocurrency getCurrency() {
        return currency;
    }

    public SviCalibration getCalibration() {
        return calibration;
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public Instant getLastRefresh() {
        return Instant.from(lastRefresh);
    }

    public boolean hasFailed(){
        return failedRefreshAttempts >= MAXIMUM_FAILED_ATTEMPTS;
    }

    // ##################################################################
    // CORE METHODS
    // ##################################################################

    public void refresh() throws SviCalibrationRefreshFailureException {

        // Query our micro-service
        BaseHttpResponse response = client.fetchSviCalibration(currency, expiry);

        // Failed to get a response
        if (Objects.isNull(response) || response.getStatusCode() != 200) {
            onFailedRefresh(true);
        }

        else {

            // Try to refresh
            try {
                VolatilitySmile sviCalibration = VolatilitySmileFactory.parseHttpResponse(response);
                updateCalibration(sviCalibration);
            }
            // if it fails, just handle it
            catch (Exception e) {
                onFailedRefresh(false);
            }
        }
    }

    // ##################################################################
    // KEEPING TRACK OF SUCCESS
    // ##################################################################

    private void onFailedRefresh(boolean connectionIssue) throws SviCalibrationRefreshFailureException {

        // This comes first, it impacts the entire system
        if(connectionIssue){
            EventBus.getDefault().postSticky(new ConnectivityLossEvent(client.getServiceProvider()));
        }

        // Local impact, check for maximum allowed failures...
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


package apps.calibration.clients;

import affresco.AffrescoCalibrationClient;
import apps.connectivity.ApiServiceProvider;
import clients.http.BaseHttpResponse;
import commons.models.expiries.Expiry;
import commons.standards.Cryptocurrency;

public class DeribitSviCalibrationClient implements SviCalibrationClient {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    // The current calibration
    private final AffrescoCalibrationClient client;

    // The external service providing the calibration:
    // in this case it's affresco calibrating data from Deribit
    private final ApiServiceProvider apiServiceProvider = ApiServiceProvider.AFFRESCO;

    // ##################################################################
    // CONSTRUCTOR
    // ##################################################################

    public DeribitSviCalibrationClient(String rootUrl) {
        this.client = new AffrescoCalibrationClient(rootUrl);
    }

    public DeribitSviCalibrationClient(String rootUrl, int port) {
        this.client = new AffrescoCalibrationClient(rootUrl, port);
    }

    // ##################################################################
    // CORE METHODS
    // ##################################################################

    @Override
    public BaseHttpResponse fetchSviCalibration(Cryptocurrency currency, Expiry expiry) {
        try {
            return client.getDeribitSmile(currency, expiry);
        }

        catch (Exception e) {
            return null;
        }
    }

    @Override
    public ApiServiceProvider getServiceProvider() {
        return null;
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public ApiServiceProvider getApiServiceProvider() {
        return apiServiceProvider;
    }
}

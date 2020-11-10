package apps.calibration.helpers;

import affresco.CalibrationAffrescoClient;
import apps.calibration.clients.SviCalibrationClient;
import clients.http.BaseHttpResponse;
import commons.standards.Cryptocurrency;

public class DeribitSviCalibrationClient implements SviCalibrationClient {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    // The targeted crypto
    private final Cryptocurrency cryptocurrency;

    // The current calibration
    private final CalibrationAffrescoClient client;

    // ##################################################################
    // CONSTRUCTOR
    // ##################################################################

    public DeribitSviCalibrationClient(Cryptocurrency cryptocurrency, String rootUrl) {
        this.cryptocurrency = cryptocurrency;
        this.client = new CalibrationAffrescoClient(rootUrl);
    }

    public DeribitSviCalibrationClient(Cryptocurrency cryptocurrency, String rootUrl, int port) {
        this.cryptocurrency = cryptocurrency;
        this.client = new CalibrationAffrescoClient(rootUrl, port);
    }

    // ##################################################################
    // CORE METHODS
    // ##################################################################

    @Override
    public BaseHttpResponse fetch(String expiry) {
        try {
            return client.getDeribitSmile(this.cryptocurrency, expiry);
        } catch (Exception e) {
            return null;
        }
    }


}

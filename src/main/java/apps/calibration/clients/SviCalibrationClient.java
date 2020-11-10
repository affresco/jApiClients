package apps.calibration.clients;

import apps.connectivity.ApiServiceProvider;
import clients.http.BaseHttpResponse;
import commons.models.expiries.Expiry;
import commons.standards.Cryptocurrency;

public interface SviCalibrationClient {

    BaseHttpResponse fetchSviCalibration(Cryptocurrency currency, Expiry expiry);

    ApiServiceProvider getServiceProvider();

}

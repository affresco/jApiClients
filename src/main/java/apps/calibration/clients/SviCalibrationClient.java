package apps.calibration.clients;

import clients.http.BaseHttpResponse;

public interface SviCalibrationClient {

    BaseHttpResponse fetch(String expiry);

}

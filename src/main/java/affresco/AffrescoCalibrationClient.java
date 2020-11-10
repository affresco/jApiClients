package affresco;

import clients.http.BaseHttpResponse;
import commons.models.expiries.Expiry;
import commons.standards.Cryptocurrency;
import deribit.models.expiries.DeribitExpiryFactory;

import java.text.ParseException;
import java.util.List;


public class AffrescoCalibrationClient extends AffrescoClient {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private static final String NAME = "calibration";
    private static final long TIMEOUT = 10000;
    private static final int VERSION = 1;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public AffrescoCalibrationClient(String serverAddress, int serverPort) {
        super(buildAddressPrefix(serverAddress, serverPort), VERSION, TIMEOUT);
    }

    public AffrescoCalibrationClient(String serverAddress) {
        super(buildAddressPrefix(serverAddress), VERSION, TIMEOUT);
    }

    // ##################################################################
    // STATIC METHODS
    // ##################################################################

    private static String buildAddressPrefix(String server, int port) {
        String part1 = String.join(":", List.of(server, Integer.toString(port)));
        return String.join("/", List.of(part1, AffrescoCalibrationClient.NAME, "v" + VERSION));
    }

    private static String buildAddressPrefix(String server) {
        return String.join("/", List.of(server, AffrescoCalibrationClient.NAME, "v" + VERSION));
    }

    // ##################################################################
    // METHODS
    // ##################################################################

    public BaseHttpResponse getDeribitSmile(Cryptocurrency currency, Expiry expiry) {
        String endpoint = String.join("/", List.of("smile/delta", currency.toString(), expiry.toString()));
        return get(endpoint);
    }

    // ##################################################################
    // MAIN
    // ##################################################################

    public static void main(String[] args) throws ParseException {
        String address = "api.affresco.io";
        AffrescoCalibrationClient c = new AffrescoCalibrationClient(address);
        var e = DeribitExpiryFactory.getInstanceFromDateString("25DEC20");
        var res = c.getDeribitSmile(Cryptocurrency.BTC, e);
        System.out.println(res.getBody());
    }


}

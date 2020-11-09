package affresco;

import clients.http.BaseHttpResponse;
import commons.standards.Cryptocurrency;

import java.util.List;


public class CalibrationAffrescoClient extends AffrescoClient {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private static final String NAME = "calibration";
    private static final long TIMEOUT = 10000;
    private static final int VERSION = 1;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public CalibrationAffrescoClient(String serverAddress, int serverPort) {
        super(buildAddressPrefix(serverAddress, serverPort), VERSION, TIMEOUT);
    }

    public CalibrationAffrescoClient(String serverAddress) {
        super(buildAddressPrefix(serverAddress), VERSION, TIMEOUT);
    }

    // ##################################################################
    // STATIC METHODS
    // ##################################################################

    private static String buildAddressPrefix(String server, int port) {
        String part1 = String.join(":", List.of(server, Integer.toString(port)));
        return String.join("/", List.of(part1, CalibrationAffrescoClient.NAME, "v" + Integer.toString(VERSION)));
    }

    private static String buildAddressPrefix(String server) {
        return String.join("/", List.of(server, CalibrationAffrescoClient.NAME, "v" + Integer.toString(VERSION)));
    }

    // ##################################################################
    // METHODS
    // ##################################################################

    public BaseHttpResponse getDeribitSmile(Cryptocurrency currency, String expiry) {
        // TODO: This should be an object implementing the Expiry Interface
        String endpoint = String.join("/", List.of("smile/delta", currency.toString(), expiry));
        return get(endpoint);
    }

    // ##################################################################
    // MAIN
    // ##################################################################

    public static void main(String[] args) {
        String address = "api.affresco.io";
        CalibrationAffrescoClient c = new CalibrationAffrescoClient(address);
        var res = c.getDeribitSmile(Cryptocurrency.BTC, "25DEC20");
        System.out.println(res.getBody());
    }

}

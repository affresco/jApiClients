package affresco;

import apps.market.Settings;
import clients.http.BaseHttpClient;
import clients.http.BaseHttpResponse;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;

public class AffrescoClient extends BaseHttpClient {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    // Logging via SLF4J
    private static Logger logger;

    // Server
    private final String serverAddress;
    private final int targetVersion;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public AffrescoClient(String serverAddress, int targetVersion, long timeout) {
        super(timeout);
        this.targetVersion = targetVersion;
        this.serverAddress = serverAddress;
    }

    public AffrescoClient(String serverAddress) {
        super();
        this.targetVersion = 1;
        this.serverAddress = serverAddress;
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
    // MAKING REQUESTS
    // ##################################################################

    public BaseHttpResponse get(String endpoint){
        URI uri = makeCompleteAddress(endpoint);
        return makeSyncGetRequest(uri);
    }

    // ##################################################################
    // FORMULATING THE ADDRESS
    // ##################################################################

    private String getVersion(){
        return "v" + this.targetVersion;
    }

    private String getAddressPrefix(){
        return String.join("/", List.of("https:/", serverAddress));
    }

    private URI makeCompleteAddress(String endpoint) {
        return URI.create(String.join("/", List.of(getAddressPrefix(), endpoint)));
    }


}

























package apps.calibration.helpers;


import apps.calibration.clients.DeribitSviCalibrationClient;
import apps.calibration.exceptions.ExchangeNotSupportedException;
import commons.models.expiries.Expiry;
import commons.standards.Cryptocurrency;
import commons.standards.DerivativeExchange;

import utilities.Marshal;

public class SviCalibrationHelperFactory {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private static final String ENVIRONMENT_KUBERNETES = "KUBERNETES";
    private static final String AFFRESCO_INTERNET_URL = "api.affresco.io";
    private static final String AFFRESCO_KUBERNETES_URL = "affresco";

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    // Static all the way
    private SviCalibrationHelperFactory() { }

    // ##################################################################
    // SUPPORT
    // ##################################################################

    private static boolean isKubernetes(){
        String isK8 = System.getenv(ENVIRONMENT_KUBERNETES);
        return Marshal.stringToBoolean(isK8);
    }

    private static String getBaseUrl(){
        if(isKubernetes()){
            return AFFRESCO_KUBERNETES_URL;
        }
        return AFFRESCO_INTERNET_URL;
    }

    // ##################################################################
    // INTERFACE
    // ##################################################################

    public static SviCalibrationHelper getInstance(DerivativeExchange exchange, Cryptocurrency currency, Expiry expiry) throws ExchangeNotSupportedException {
        if (exchange == DerivativeExchange.DERIBIT){
            return getDeribitInstance(currency, expiry);
        }

        // Other exchanges got here... eventually...
        throw new ExchangeNotSupportedException("Calibration for " + exchange.toString() + " is not supported.");
    }

    // ##################################################################
    // DERIBIT
    // ##################################################################

    private static SviCalibrationHelper getDeribitInstance(Cryptocurrency currency, Expiry expiry){
        DeribitSviCalibrationClient client = new DeribitSviCalibrationClient(getBaseUrl());
        return new SviCalibrationHelper(currency, expiry, client);
    }

    // ##################################################################
    // QUEDEX
    // ##################################################################

    // pass for now


}

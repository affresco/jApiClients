package jClient.delta;

import jClient.delta.delegates.AccountDelegate;
import jClient.delta.delegates.MarketDelegate;
import jClient.delta.delegates.OrdersDelegate;
import jClient.delta.delegates.TradingDelegate;
import jClient.delta.endpoints.SessionEndpoints;
import jClient.delta.factories.TradingMessage;

public class RequestClient extends DeltaClientMonitor {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################


    // Delegates for making requests
    public AccountDelegate account;
    public TradingDelegate trading;
    public MarketDelegate market;
    public OrdersDelegate orders;


    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public RequestClient(String url, DeltaCredentials credentials) {
        super(url, credentials);
        setupDelegates();
    }

    // ##################################################################
    // SETUP DELEGATES
    // ##################################################################

    private void setupDelegates(){
        account = new AccountDelegate(client);
        trading = new TradingDelegate(client);
        market = new MarketDelegate(client);
        orders = new OrdersDelegate(client);
    }

    // ##################################################################
    // MAIN
    // ##################################################################

    public static void main(String[] args) throws InterruptedException {

        // This will be replaced in the future
        String key = new String("5Bt7EVi8");
        String secret = new String("zavOtsOIvTkAprQz1UKhz-35TxHOPIscMYgsAYzJFYY");
        String url = new String("wss://test.deribit.com/ws/api/v2");

        // Credentials
        DeltaCredentials credentials = new DeltaCredentials(key, secret, url);
        DeltaClientMonitor c = new DeltaClientMonitor(url, credentials);
        // String res = c.getPositions("BTC-PERPETUAL");

        int i = 0;
        while (i < 1e19) {
            i++;
            Thread.sleep(1000);
        }

    }

}

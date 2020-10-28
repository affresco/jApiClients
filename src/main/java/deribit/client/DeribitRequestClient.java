package deribit.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import deribit.client.delegates.AccountDelegate;
import deribit.client.delegates.MarketDelegate;
import deribit.client.delegates.OrdersDelegate;
import deribit.client.delegates.TradingDelegate;
import deribit.models.positions.DeltaPositionMessage;

public class DeribitRequestClient extends DeribitClientMonitor {

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

    public DeribitRequestClient(String url, DeribitCredentials credentials) {
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
        DeribitCredentials credentials = new DeribitCredentials(key, secret, url);
        DeribitRequestClient c = new DeribitRequestClient(url, credentials);
        String res = c.account.getPosition("BTC-PERPETUAL");

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            double start = System.nanoTime();

            DeltaPositionMessage msg = objectMapper.readValue(res, DeltaPositionMessage.class);

            double end = System.nanoTime();
            double elapsed = (end - start) / 1000000.0;
            System.out.println("Time: " + elapsed);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        int i = 0;
        while (i < 1e19) {
            i++;
            Thread.sleep(1000);
        }

    }

}

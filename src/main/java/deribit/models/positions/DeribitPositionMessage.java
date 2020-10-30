package deribit.models.positions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;

public class DeribitPositionMessage {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    // Message ID and protocol
    private String jsonrpc;
    private String id;

    // Timing
    private double usIn;
    private double usOut;
    private double usDiff;

    // Is this on the testnet ?
    private boolean testnet;

    // Content
    private double totalProfitLoss;
    private double sizeCurrency;
    private double size;
    private double settlementPrice;
    private double realizedProfitLoss;
    private double realizedFunding;
    private double openOrdersMargin;
    private double markPrice;
    private double maintenanceMargin;
    private double leverage;
    private String kind;
    private String instrument;
    private double initialMargin;
    private double indexPrice;
    private double floatingProfitLoss;
    private double estimatedLiquidationPrice;
    private String direction;
    private double delta;
    private double averagePrice;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public DeribitPositionMessage(){}

    // ##################################################################
    // GETTERS
    // ##################################################################

    public boolean isTestnet() {
        return testnet;
    }

    public double getTotalProfitLoss() {
        return totalProfitLoss;
    }

    public double getSizeCurrency() {
        return sizeCurrency;
    }

    public double getSize() {
        return size;
    }

    public double getSettlementPrice() {
        return settlementPrice;
    }

    public double getRealizedProfitLoss() {
        return realizedProfitLoss;
    }

    public double getRealizedFunding() {
        return realizedFunding;
    }

    public double getOpenOrdersMargin() {
        return openOrdersMargin;
    }

    public double getMarkPrice() {
        return markPrice;
    }

    public double getMaintenanceMargin() {
        return maintenanceMargin;
    }

    public double getLeverage() {
        return leverage;
    }

    public String getKind() {
        return kind;
    }

    public String getInstrument() {
        return instrument;
    }

    public double getInitialMargin() {
        return initialMargin;
    }

    public double getIndexPrice() {
        return indexPrice;
    }

    public double getFloatingProfitLoss() {
        return floatingProfitLoss;
    }

    public double getEstimatedLiquidationPrice() {
        return estimatedLiquidationPrice;
    }

    public String getDirection() {
        return direction;
    }

    public double getDelta() {
        return delta;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public String getJsonrpc(){
        return this.jsonrpc;
    }
    public String getId(){
        return this.id;
    }

    public double getUsIn(){
        return this.usIn;
    }

    public double getUsOut(){
        return this.usOut;
    }

    public boolean getTestnet(){
        return this.testnet;
    }


    public double getUsDiff(){
        return this.usDiff;
    }
    // ##################################################################
    // SETTERS -- LOADING WITH JACKSON DATABIND
    // ##################################################################

    @JsonProperty("jsonrpc")
    public void setJsonrpc(String value){
        this.jsonrpc = value;
    }

    @JsonProperty("id")
    public void setId(String value){
        this.id = value;
    }

    @JsonProperty("usIn")
    public void setUsIn(double value){
        this.usIn = value * 0.0001; // TODO this is where i was...
    }

    @JsonProperty("usOut")
    public void setUsOut(double value){
       this.usOut = value * 0.0001; // TODO this is where i was...
    }

    @JsonProperty("usDiff")
    public void setUsDiff(double value){
        this.usDiff = value;
    }

    @JsonProperty("testnet")
    public void setTestnet(boolean value){
        this.testnet = value;
    }

    @JsonProperty("result")
    public void setResult(LinkedHashMap<String, String> value){
        totalProfitLoss = Double.parseDouble(value.get("total_profit_loss"));
        sizeCurrency = Double.parseDouble(value.get("size_currency"));
        size = Double.parseDouble(value.get("size"));
        settlementPrice = Double.parseDouble(value.get("settlement_price"));
        realizedProfitLoss = Double.parseDouble(value.get("realized_profit_loss"));
        realizedFunding = Double.parseDouble(value.get("realized_funding"));
        openOrdersMargin = Double.parseDouble(value.get("open_orders_margin"));
        markPrice = Double.parseDouble(value.get("mark_price"));
        maintenanceMargin = Double.parseDouble(value.get("maintenance_margin"));
        leverage = Double.parseDouble(value.get("leverage"));
        kind = value.get("kind");
        instrument = value.get("instrument_name");
        initialMargin = Double.parseDouble(value.get("initial_margin"));
        indexPrice = Double.parseDouble(value.get("index_price"));
        floatingProfitLoss = Double.parseDouble(value.get("floating_profit_loss"));
        estimatedLiquidationPrice = Double.parseDouble(value.get("estimated_liquidation_price"));
        direction = value.get("direction");
        delta = Double.parseDouble(value.get("delta"));
        averagePrice = Double.parseDouble(value.get("average_price"));
    }


}
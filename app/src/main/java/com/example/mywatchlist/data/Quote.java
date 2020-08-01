package com.example.mywatchlist.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static android.graphics.Color.rgb;

public class Quote implements Serializable {

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("primaryExchange")
    @Expose
    private String primaryExchange;
    @SerializedName("calculationPrice")
    @Expose
    private String calculationPrice;
    @SerializedName("open")
    @Expose
    private double open;
//    @SerializedName("openTime")
//    @Expose
//    private int openTime;


    @SerializedName("openTime")
    @Expose
    private float openTime;

    @SerializedName("openSource")
    @Expose
    private String openSource;
    @SerializedName("close")
    @Expose
    private double close;
//    @SerializedName("closeTime")
//    @Expose
//    private int closeTime;


    @SerializedName("closeTime")
    @Expose
    private float closeTime;



    @SerializedName("closeSource")
    @Expose
    private String closeSource;
    @SerializedName("high")
    @Expose
    private double high;
//    @SerializedName("highTime")
//    @Expose
//    private int highTime;

    @SerializedName("highTime")
    @Expose
    private float highTime;


    @SerializedName("highSource")
    @Expose
    private String highSource;
    @SerializedName("low")
    @Expose
    private double low;
//    @SerializedName("lowTime")
//    @Expose
//    private int lowTime;

    @SerializedName("lowTime")
    @Expose
    private float lowTime;


    @SerializedName("lowSource")
    @Expose
    private String lowSource;
    @SerializedName("latestPrice")
    @Expose
    private double latestPrice;
    @SerializedName("latestSource")
    @Expose
    private String latestSource;
    @SerializedName("latestTime")
    @Expose
    private String latestTime;
//    @SerializedName("latestUpdate")
//    @Expose
//    private int latestUpdate;

    @SerializedName("latestUpdate")
    @Expose
    private float latestUpdate;


    @SerializedName("latestVolume")
    @Expose
    private int latestVolume;
    @SerializedName("iexRealtimePrice")
    @Expose
    private Object iexRealtimePrice;
    @SerializedName("iexRealtimeSize")
    @Expose
    private Object iexRealtimeSize;
    @SerializedName("iexLastUpdated")
    @Expose
    private Object iexLastUpdated;
    @SerializedName("delayedPrice")
    @Expose
    private double delayedPrice;
//    @SerializedName("delayedPriceTime")
//    @Expose
//    private int delayedPriceTime;

    @SerializedName("delayedPriceTime")
    @Expose
    private float delayedPriceTime;


    @SerializedName("extendedPrice")
    @Expose
    private double extendedPrice;
    @SerializedName("extendedChange")
    @Expose
    private double extendedChange;
    @SerializedName("extendedChangePercent")
    @Expose
    private double extendedChangePercent;

    @SerializedName("previousClose")
    @Expose
    private double previousClose;
    @SerializedName("previousVolume")
    @Expose
    private int previousVolume;
    @SerializedName("change")
    @Expose
    private double change;
    @SerializedName("changePercent")
    @Expose
    private double changePercent;
    @SerializedName("volume")
    @Expose
    private int volume;
    @SerializedName("iexMarketPercent")
    @Expose
    private Object iexMarketPercent;
    @SerializedName("iexVolume")
    @Expose
    private Object iexVolume;
    @SerializedName("avgTotalVolume")
    @Expose
    private int avgTotalVolume;
    @SerializedName("iexBidPrice")
    @Expose
    private Object iexBidPrice;
    @SerializedName("iexBidSize")
    @Expose
    private Object iexBidSize;
    @SerializedName("iexAskPrice")
    @Expose
    private Object iexAskPrice;
    @SerializedName("iexAskSize")
    @Expose
    private Object iexAskSize;
    @SerializedName("iexOpen")
    @Expose
    private Object iexOpen;
    @SerializedName("iexOpenTime")
    @Expose
    private Object iexOpenTime;
    @SerializedName("iexClose")
    @Expose
    private double iexClose;
    @SerializedName("marketCap")
    @Expose
    private float marketCap;
    @SerializedName("peRatio")
    @Expose
    private double peRatio;
    @SerializedName("week52High")
    @Expose
    private double week52High;
    @SerializedName("week52Low")
    @Expose
    private double week52Low;
    @SerializedName("ytdChange")
    @Expose
    private double ytdChange;
    @SerializedName("isUSMarketOpen")
    @Expose
    private boolean isUSMarketOpen;

    // xue
    private int color;
    private String plusOrMinor;

    public Quote(String symbol, String companyName, String primaryExchange, String calculationPrice, double open, float openTime, String openSource, double close, float closeTime, String closeSource, double high, float highTime, String highSource, double low, float lowTime, String lowSource, double latestPrice, String latestSource, String latestTime, float latestUpdate, int latestVolume, Object iexRealtimePrice, Object iexRealtimeSize, Object iexLastUpdated, double delayedPrice, float delayedPriceTime, double extendedPrice, double extendedChange, double extendedChangePercent,  double previousClose, int previousVolume, double change, double changePercent, int volume, Object iexMarketPercent, Object iexVolume, int avgTotalVolume, Object iexBidPrice, Object iexBidSize, Object iexAskPrice, Object iexAskSize, Object iexOpen, Object iexOpenTime, double iexClose, float marketCap, double peRatio, double week52High, double week52Low, double ytdChange,  boolean isUSMarketOpen) {
        super();
        this.symbol = symbol;
        this.companyName = companyName;
        this.primaryExchange = primaryExchange;
        this.calculationPrice = calculationPrice;
        this.open = open;
        this.openTime = openTime;
        this.openSource = openSource;
        this.close = close;
        this.closeTime = closeTime;
        this.closeSource = closeSource;
        this.high = high;
        this.highTime = highTime;
        this.highSource = highSource;
        this.low = low;
        this.lowTime = lowTime;
        this.lowSource = lowSource;
        this.latestPrice = latestPrice;
        this.latestSource = latestSource;
        this.latestTime = latestTime;
        this.latestUpdate = latestUpdate;
        this.latestVolume = latestVolume;
        this.iexRealtimePrice = iexRealtimePrice;
        this.iexRealtimeSize = iexRealtimeSize;
        this.iexLastUpdated = iexLastUpdated;
        this.delayedPrice = delayedPrice;
        this.delayedPriceTime = delayedPriceTime;
        this.extendedPrice = extendedPrice;
        this.extendedChange = extendedChange;
        this.extendedChangePercent = extendedChangePercent;
        this.previousClose = previousClose;
        this.previousVolume = previousVolume;
        this.change = change;
        this.changePercent = changePercent;
        this.volume = volume;
        this.iexMarketPercent = iexMarketPercent;
        this.iexVolume = iexVolume;
        this.avgTotalVolume = avgTotalVolume;
        this.iexBidPrice = iexBidPrice;
        this.iexBidSize = iexBidSize;
        this.iexAskPrice = iexAskPrice;
        this.iexAskSize = iexAskSize;
        this.iexOpen = iexOpen;
        this.iexOpenTime = iexOpenTime;
        this.iexClose = iexClose;
        this.marketCap = marketCap;
        this.peRatio = peRatio;
        this.week52High = week52High;
        this.week52Low = week52Low;
        this.ytdChange = ytdChange;
        this.isUSMarketOpen = isUSMarketOpen;

        // xue
        if (this.change > 0){
            color = rgb(15,157,88);
            plusOrMinor = "+";
        } else if (change < 0){
            color = rgb(219,68,55);
            plusOrMinor = "–";
        } else{
            color = rgb(171,171,171);
            plusOrMinor = "";
        }

    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    public String getCalculationPrice() {
        return calculationPrice;
    }

    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public float getOpenTime() {
        return openTime;
    }

    public void setOpenTime(int openTime) {
        this.openTime = openTime;
    }

    public String getOpenSource() {
        return openSource;
    }

    public void setOpenSource(String openSource) {
        this.openSource = openSource;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public float getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(int closeTime) {
        this.closeTime = closeTime;
    }

    public String getCloseSource() {
        return closeSource;
    }

    public void setCloseSource(String closeSource) {
        this.closeSource = closeSource;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public float getHighTime() {
        return highTime;
    }

    public void setHighTime(int highTime) {
        this.highTime = highTime;
    }

    public String getHighSource() {
        return highSource;
    }

    public void setHighSource(String highSource) {
        this.highSource = highSource;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public float getLowTime() {
        return lowTime;
    }

    public void setLowTime(int lowTime) {
        this.lowTime = lowTime;
    }

    public String getLowSource() {
        return lowSource;
    }

    public void setLowSource(String lowSource) {
        this.lowSource = lowSource;
    }

    public double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(double latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getLatestSource() {
        return latestSource;
    }

    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    public String getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    public float getLatestUpdate() {
        return latestUpdate;
    }

    public void setLatestUpdate(int latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    public int getLatestVolume() {
        return latestVolume;
    }

    public void setLatestVolume(int latestVolume) {
        this.latestVolume = latestVolume;
    }

    public Object getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    public void setIexRealtimePrice(Object iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    public Object getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    public void setIexRealtimeSize(Object iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    public Object getIexLastUpdated() {
        return iexLastUpdated;
    }

    public void setIexLastUpdated(Object iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }

    public double getDelayedPrice() {
        return delayedPrice;
    }

    public void setDelayedPrice(double delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    public float getDelayedPriceTime() {
        return delayedPriceTime;
    }

    public void setDelayedPriceTime(int delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    public double getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(double extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    public double getExtendedChange() {
        return extendedChange;
    }

    public void setExtendedChange(double extendedChange) {
        this.extendedChange = extendedChange;
    }

    public double getExtendedChangePercent() {
        return extendedChangePercent;
    }

    public void setExtendedChangePercent(double extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }


    public double getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(double previousClose) {
        this.previousClose = previousClose;
    }

    public int getPreviousVolume() {
        return previousVolume;
    }

    public void setPreviousVolume(int previousVolume) {
        this.previousVolume = previousVolume;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(double changePercent) {
        this.changePercent = changePercent;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Object getIexMarketPercent() {
        return iexMarketPercent;
    }

    public void setIexMarketPercent(Object iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    public Object getIexVolume() {
        return iexVolume;
    }

    public void setIexVolume(Object iexVolume) {
        this.iexVolume = iexVolume;
    }

    public int getAvgTotalVolume() {
        return avgTotalVolume;
    }

    public void setAvgTotalVolume(int avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    public Object getIexBidPrice() {
        return iexBidPrice;
    }

    public void setIexBidPrice(Object iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    public Object getIexBidSize() {
        return iexBidSize;
    }

    public void setIexBidSize(Object iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    public Object getIexAskPrice() {
        return iexAskPrice;
    }

    public void setIexAskPrice(Object iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    public Object getIexAskSize() {
        return iexAskSize;
    }

    public void setIexAskSize(Object iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    public Object getIexOpen() {
        return iexOpen;
    }

    public void setIexOpen(Object iexOpen) {
        this.iexOpen = iexOpen;
    }

    public Object getIexOpenTime() {
        return iexOpenTime;
    }

    public void setIexOpenTime(Object iexOpenTime) {
        this.iexOpenTime = iexOpenTime;
    }

    public double getIexClose() {
        return iexClose;
    }

    public void setIexClose(double iexClose) {
        this.iexClose = iexClose;
    }


    public float getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(int marketCap) {
        this.marketCap = marketCap;
    }

    public double getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(double peRatio) {
        this.peRatio = peRatio;
    }

    public double getWeek52High() {
        return week52High;
    }

    public void setWeek52High(double week52High) {
        this.week52High = week52High;
    }

    public double getWeek52Low() {
        return week52Low;
    }

    public void setWeek52Low(double week52Low) {
        this.week52Low = week52Low;
    }

    public double getYtdChange() {
        return ytdChange;
    }

    public void setYtdChange(double ytdChange) {
        this.ytdChange = ytdChange;
    }


    public boolean isIsUSMarketOpen() {
        return isUSMarketOpen;
    }

    public void setIsUSMarketOpen(boolean isUSMarketOpen) {
        this.isUSMarketOpen = isUSMarketOpen;
    }

    public int getColor() {
        return color;
    }

    public String getPlusOrMinor() {
        return plusOrMinor;
    }
}
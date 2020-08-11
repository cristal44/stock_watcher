package com.example.mywatchlist.entity;

import com.example.mywatchlist.MyColor;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static android.graphics.Color.rgb;
import static com.example.mywatchlist.Utils.formatValue;

public class Quote implements Serializable ,StockData{

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
    @SerializedName("openTime")
    @Expose
    private float openTime;
    @SerializedName("openSource")
    @Expose
    private String openSource;
    @SerializedName("close")
    @Expose
    private double close;
    @SerializedName("closeTime")
    @Expose
    private float closeTime;
    @SerializedName("closeSource")
    @Expose
    private String closeSource;
    @SerializedName("high")
    @Expose
    private double high;
    @SerializedName("highTime")
    @Expose
    private float highTime;
    @SerializedName("highSource")
    @Expose
    private String highSource;
    @SerializedName("low")
    @Expose
    private double low;
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



    public String getPlusOrMinors(){
        return change > 0 ? "+" : (change < 0 ? "-" : "");
    }

    public int getColor(){
        return change > 0 ? MyColor.GREEN : (change < 0 ? MyColor.RED : MyColor.GREY);
    }

    public String getUpOrDownArrow(){
        return change > 0 ? "▲" : (change < 0 ? "▼" : "");
    }


    public String getSymbol() {
        return symbol;
    }


    public String getCompanyName() {
        return companyName;
    }


    public String getPrimaryExchange() {
        return primaryExchange;
    }


    public String getCalculationPrice() {
        return calculationPrice;
    }


    public double getOpen() {
        return open;
    }


    public float getOpenTime() {
        return openTime;
    }


    public String getOpenSource() {
        return openSource;
    }


    public double getClose() {
        return close;
    }


    public float getCloseTime() {
        return closeTime;
    }


    public String getCloseSource() {
        return closeSource;
    }


    public double getHigh() {
        return high;
    }


    public float getHighTime() {
        return highTime;
    }


    public String getHighSource() {
        return highSource;
    }


    public double getLow() {
        return low;
    }


    public float getLowTime() {
        return lowTime;
    }


    public String getLowSource() {
        return lowSource;
    }


    public double getLatestPrice() {
        return latestPrice;
    }


    public String getLatestSource() {
        return latestSource;
    }


    public String getLatestTime() {
        return latestTime;
    }


    public float getLatestUpdate() {
        return latestUpdate;
    }


    public int getLatestVolume() {
        return latestVolume;
    }


    public Object getIexRealtimePrice() {
        return iexRealtimePrice;
    }


    public Object getIexRealtimeSize() {
        return iexRealtimeSize;
    }


    public Object getIexLastUpdated() {
        return iexLastUpdated;
    }


    public double getDelayedPrice() {
        return delayedPrice;
    }


    public float getDelayedPriceTime() {
        return delayedPriceTime;
    }

    public double getExtendedPrice() {
        return extendedPrice;
    }


    public double getExtendedChange() {
        return extendedChange;
    }


    public double getExtendedChangePercent() {
        return extendedChangePercent;
    }


    public double getPreviousClose() {
        return previousClose;
    }


    public int getPreviousVolume() {
        return previousVolume;
    }


    public double getChange() {
        return change;
    }


    public double getChangePercent() {
        return changePercent;
    }

    public String getVolume() {
        return formatValue(volume);
    }


    public Object getIexMarketPercent() {
        return iexMarketPercent;
    }


    public Object getIexVolume() {
        return iexVolume;
    }


    public String getAvgTotalVolume() {
        return formatValue(avgTotalVolume);
    }


    public Object getIexBidPrice() {
        return iexBidPrice;
    }


    public Object getIexBidSize() {
        return iexBidSize;
    }


    public Object getIexAskPrice() {
        return iexAskPrice;
    }


    public Object getIexAskSize() {
        return iexAskSize;
    }


    public Object getIexOpen() {
        return iexOpen;
    }


    public Object getIexOpenTime() {
        return iexOpenTime;
    }


    public double getIexClose() {
        return iexClose;
    }

    public String getMarketCap() {
        return formatValue(marketCap);
    }

    public double getPeRatio() {
        return peRatio;
    }


    public double getWeek52High() {
        return week52High;
    }

    public double getWeek52Low() {
        return week52Low;
    }


    public double getYtdChange() {
        return ytdChange;
    }


    public boolean isIsUSMarketOpen() {
        return isUSMarketOpen;
    }


}
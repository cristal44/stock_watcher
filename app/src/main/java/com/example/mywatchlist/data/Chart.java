package com.example.mywatchlist.data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chart {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("open")
    @Expose
    private Double open;
    @SerializedName("close")
    @Expose
    private Double close;
    @SerializedName("high")
    @Expose
    private Double high;
    @SerializedName("low")
    @Expose
    private Double low;
    @SerializedName("volume")
    @Expose
    private Integer volume;
    @SerializedName("uOpen")
    @Expose
    private Double uOpen;
    @SerializedName("uClose")
    @Expose
    private Double uClose;
    @SerializedName("uHigh")
    @Expose
    private Double uHigh;
    @SerializedName("uLow")
    @Expose
    private Double uLow;
    @SerializedName("uVolume")
    @Expose
    private Integer uVolume;
    @SerializedName("change")
    @Expose
    private Double change;
    @SerializedName("changePercent")
    @Expose
    private Double changePercent;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("changeOverTime")
    @Expose
    private Double changeOverTime;

    /**
     * @param date
     * @param uHigh
     * @param uVolume
     * @param change
     * @param uOpen
     * @param uLow
     * @param uClose
     * @param label
     * @param volume
     * @param high
     * @param low
     * @param changeOverTime
     * @param changePercent
     * @param close
     * @param open
     */
    public Chart(String date, Double open, Double close, Double high, Double low, Integer volume, Double uOpen, Double uClose, Double uHigh, Double uLow, Integer uVolume, Double change, Double changePercent, String label, Double changeOverTime) {
        super();
        this.date = date;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.uOpen = uOpen;
        this.uClose = uClose;
        this.uHigh = uHigh;
        this.uLow = uLow;
        this.uVolume = uVolume;
        this.change = change;
        this.changePercent = changePercent;
        this.label = label;
        this.changeOverTime = changeOverTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Double getUOpen() {
        return uOpen;
    }

    public void setUOpen(Double uOpen) {
        this.uOpen = uOpen;
    }

    public Double getUClose() {
        return uClose;
    }

    public void setUClose(Double uClose) {
        this.uClose = uClose;
    }

    public Double getUHigh() {
        return uHigh;
    }

    public void setUHigh(Double uHigh) {
        this.uHigh = uHigh;
    }

    public Double getULow() {
        return uLow;
    }

    public void setULow(Double uLow) {
        this.uLow = uLow;
    }

    public Integer getUVolume() {
        return uVolume;
    }

    public void setUVolume(Integer uVolume) {
        this.uVolume = uVolume;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public Double getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(Double changePercent) {
        this.changePercent = changePercent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getChangeOverTime() {
        return changeOverTime;
    }

    public void setChangeOverTime(Double changeOverTime) {
        this.changeOverTime = changeOverTime;
    }

}
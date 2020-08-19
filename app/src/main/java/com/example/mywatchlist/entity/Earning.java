package com.example.mywatchlist.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Earning implements Serializable {

    @SerializedName("actualEPS")
    @Expose
    private Double actualEPS;
    @SerializedName("consensusEPS")
    @Expose
    private Double consensusEPS;
    @SerializedName("announceTime")
    @Expose
    private String announceTime;
    @SerializedName("numberOfEstimates")
    @Expose
    private Integer numberOfEstimates;
    @SerializedName("EPSSurpriseDollar")
    @Expose
    private Double ePSSurpriseDollar;
    @SerializedName("EPSReportDate")
    @Expose
    private String ePSReportDate;
    @SerializedName("fiscalPeriod")
    @Expose
    private String fiscalPeriod;
    @SerializedName("fiscalEndDate")
    @Expose
    private String fiscalEndDate;
    @SerializedName("yearAgo")
    @Expose
    private Double yearAgo;
    @SerializedName("yearAgoChangePercent")
    @Expose
    private Double yearAgoChangePercent;
    @SerializedName("currency")
    @Expose
    private Object currency;

    public Double getActualEPS() {
        return actualEPS;
    }

    public void setActualEPS(Double actualEPS) {
        this.actualEPS = actualEPS;
    }

    public Double getConsensusEPS() {
        return consensusEPS;
    }

    public void setConsensusEPS(Double consensusEPS) {
        this.consensusEPS = consensusEPS;
    }

    public String getAnnounceTime() {
        return announceTime;
    }

    public void setAnnounceTime(String announceTime) {
        this.announceTime = announceTime;
    }

    public Integer getNumberOfEstimates() {
        return numberOfEstimates;
    }

    public void setNumberOfEstimates(Integer numberOfEstimates) {
        this.numberOfEstimates = numberOfEstimates;
    }

    public Double getEPSSurpriseDollar() {
        return ePSSurpriseDollar;
    }

    public void setEPSSurpriseDollar(Double ePSSurpriseDollar) {
        this.ePSSurpriseDollar = ePSSurpriseDollar;
    }

    public String getEPSReportDate() {
        return ePSReportDate;
    }

    public void setEPSReportDate(String ePSReportDate) {
        this.ePSReportDate = ePSReportDate;
    }

    public String getFiscalPeriod() {
        return fiscalPeriod;
    }

    public void setFiscalPeriod(String fiscalPeriod) {
        this.fiscalPeriod = fiscalPeriod;
    }

    public String getFiscalEndDate() {
        return fiscalEndDate;
    }

    public void setFiscalEndDate(String fiscalEndDate) {
        this.fiscalEndDate = fiscalEndDate;
    }

    public Double getYearAgo() {
        return yearAgo;
    }

    public void setYearAgo(Double yearAgo) {
        this.yearAgo = yearAgo;
    }

    public Double getYearAgoChangePercent() {
        return yearAgoChangePercent;
    }

    public void setYearAgoChangePercent(Double yearAgoChangePercent) {
        this.yearAgoChangePercent = yearAgoChangePercent;
    }

    public Object getCurrency() {
        return currency;
    }

    public void setCurrency(Object currency) {
        this.currency = currency;
    }

}
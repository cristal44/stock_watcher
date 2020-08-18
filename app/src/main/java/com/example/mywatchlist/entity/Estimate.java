package com.example.mywatchlist.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Estimate implements Serializable {
    @SerializedName("consensusEPS")
    @Expose
    public Double consensusEPS;
    @SerializedName("announceTime")
    @Expose
    public String announceTime;
    @SerializedName("numberOfEstimates")
    @Expose
    public Integer numberOfEstimates;
    @SerializedName("reportDate")
    @Expose
    public String reportDate;
    @SerializedName("fiscalPeriod")
    @Expose
    public String fiscalPeriod;
    @SerializedName("fiscalEndDate")
    @Expose
    public String fiscalEndDate;
    @SerializedName("currency")
    @Expose
    public Object currency;

    public Double getConsensusEPS() {
        return consensusEPS;
    }

    public Integer getNumberOfEstimates() {
        return numberOfEstimates;
    }

    public String getReportDate() {
        return reportDate;
    }

    public String getFiscalPeriod() {
        return fiscalPeriod;
    }

    public String getFiscalEndDate() {
        return fiscalEndDate;
    }

    public Object getCurrency() {
        return currency;
    }

    public String getAnnounceTime(){
        switch (announceTime){
            case "BTO":
                return "Before Open";
            case "AMC":
                return "After Close";
            case "DMT":
                return "During Market";
        }

        return "-";
    }
}

package com.example.mywatchlist.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpcomingEarning implements Serializable {
    @SerializedName("symbol")
    @Expose
    public String symbol;
    @SerializedName("reportDate")
    @Expose
    public String reportDate;

    public String getSymbol() {
        return symbol;
    }

    public String getReportDate() {
        return reportDate;
    }
}



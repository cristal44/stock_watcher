package com.example.mywatchlist.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Estimates implements Serializable {

    @SerializedName("symbol")
    @Expose
    public String symbol;
    @SerializedName("estimates")
    @Expose
    public List<Estimate> estimates = null;

    public String getSymbol() {
        return symbol;
    }

    public List<Estimate> getEstimates() {
        return estimates;
    }
}

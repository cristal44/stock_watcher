package com.example.mywatchlist.entity;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Earnings implements Serializable {

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("earnings")
    @Expose
    private List<Earning> earnings = null;

    public String getSymbol() {
        return symbol;
    }

    public List<Earning> getEarnings() {
        return earnings;
    }
}
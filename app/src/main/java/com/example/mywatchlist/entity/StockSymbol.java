package com.example.mywatchlist.entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class StockSymbol implements StockData, Serializable {

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
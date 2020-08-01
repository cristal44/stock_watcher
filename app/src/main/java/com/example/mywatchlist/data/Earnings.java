package com.example.mywatchlist.data;

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

    /**
     *
     * @param symbol
     * @param earnings
     */
    public Earnings(String symbol, List<Earning> earnings) {
        super();
        this.symbol = symbol;
        this.earnings = earnings;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Earning> getEarnings() {
        return earnings;
    }

    public void setEarnings(List<Earning> earnings) {
        this.earnings = earnings;
    }

}
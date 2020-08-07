package com.example.mywatchlist.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Charts implements StockData {

    @SerializedName("range")
    @Expose
    public String range;
    @SerializedName("data")
    @Expose
    public List<Data> data = null;

    public String getRange() {
        return range;
    }

    public List<Data> getData() {
        return data;
    }

    @Override
    public void display() {

    }
}

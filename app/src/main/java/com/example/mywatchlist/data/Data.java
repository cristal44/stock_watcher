package com.example.mywatchlist.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("minute")
    @Expose
    public String minute;
    @SerializedName("label")
    @Expose
    public String label;
    @SerializedName("high")
    @Expose
    public float high;


    public String getDate() {
        return date;
    }

    public String getMinute() {
        return minute;
    }

    public String getLabel() {
        return label;
    }

    public float getHigh() {
        return high;
    }
}

package com.example.mywatchlist.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import static com.example.mywatchlist.Utils.formatValue;

public class Data implements StockData {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("minute")
    @Expose
    private String minute;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("high")
    @Expose
    private Double high;
    @SerializedName("low")
    @Expose
    private Double low;
    @SerializedName("open")
    @Expose
    private Double open;
    @SerializedName("close")
    @Expose
    private Double close;
    @SerializedName("average")
    @Expose
    private Double average;
    @SerializedName("volume")
    @Expose
    private float volume;

    private String xValueString;


    public String getDate() {
        String[] dateString = date.split("-");
        String year = dateString[0];
        String month = getMonth(dateString[1]);
        String date1 = dateString[2];
        return month + " " + date1 + ", " + year;
    }

    public String getMonth(String month) {
        switch (month) {
            case "01":
                return "Jan";
            case "02":
                return "Feb";
            case "03":
                return "Mar";
            case "04":
                return "Apr";
            case "05":
                return "May";
            case "06":
                return "Jun";
            case "07":
                return "Jul";
            case "08":
                return "Aug";
            case "09":
                return "Sep";
            case "10":
                return "Oct";
            case "11":
                return "Nov";
            case "12":
                return "Dec";
        }

        return "";

    }

    public String getMinute() {
        return minute;
    }

    public String getLabel() {
        return label;
    }

    public float getHigh() {
        return high == null ? 0f : high.floatValue();
    }

    public String getHighString() {
        return String.format("%.2f", high);
    }

    public String getLow() {
        return String.format("%.2f", low);
    }

    public String getOpen() {
        return String.format("%.2f", open);
    }

    public String getClose() {
        return String.format("%.2f", close);
    }

    public String getAverage() {
        return average == null ? "-" : String.format("%.2f", average);
    }

    public String getVolumeString() {
        return formatValue(volume);
    }


    public float getVolume() {
        return volume;
    }

    public void setxValueString(String xValueString){
        this.xValueString = xValueString;
    }

    public String getxValueString(){
        return xValueString;
    }

    @Override
    public String getSymbol() {
        return null;
    }
}

package com.example.mywatchlist.data;


import java.io.Serializable;

import static android.graphics.Color.rgb;

//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class Stock {
//
//    @SerializedName("quote")
//    @Expose
//    private Quote quote;
//
//    public Stock(Quote quote) {
//        super();
//        this.quote = quote;
//    }
//
//    public Quote getQuote() {
//        return quote;
//    }
//
//    public void setQuote(Quote quote) {
//        this.quote = quote;
//    }
//
//}

public class Stock implements Serializable {
    private String symbol;
    private double price;
    private String companyName;
    private double change;
    private double percentage;
    private String plusOrMinor;
    private int color;
    private double pe;

    public Stock(String symbol, double price, String companyName, double change, double percentage) {
        this.symbol = symbol;
        this.price = price;
        this.companyName = companyName;
        this.change = change;
        this.percentage = percentage;
        this.pe = 20.44;

        if (change > 0){
            color = rgb(15,157,88);
            plusOrMinor = "+";
        } else if (change < 0){
            color = rgb(219,68,55);
            plusOrMinor = "–";
        } else{
            color = rgb(171,171,171);
            plusOrMinor = "";
        }
    }

    public String getPe() {return String.format("%.2f",pe);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPrice() {
        return String.format("%.2f",price);
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getChange() {
        return String.format("%.2f",change);
    }

    public String getPercentage() {
        return String.format("%.2f%%",percentage);
    }

    public String getPlusOrMinor() {
        return plusOrMinor;
    }

    public int getColor() {
        return color;
    }
}

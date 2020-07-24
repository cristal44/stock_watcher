package com.example.mywatchlist;


import java.io.Serializable;

import static android.graphics.Color.rgb;

public class Stock implements Serializable {
    private String symbol;
    private double price;
    private String companyName;
    private double change;
    private double percentage;
    private String plusOrMinor;
    private int color;

    public Stock(String symbol, double price, String companyName, double change, double percentage) {
        this.symbol = symbol;
        this.price = price;
        this.companyName = companyName;
        this.change = change;
        this.percentage = percentage;

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

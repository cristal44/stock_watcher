package com.example.mywatchlist.entity;


import com.example.mywatchlist.MyColor;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Stock implements StockData, Serializable{

    @SerializedName("quote")
    @Expose
    private Quote quote;
    @SerializedName("news")
    @Expose
    private List<News> news = null;
    @SerializedName("company")
    @Expose
    private Company company;
    @SerializedName("earnings")
    @Expose
    private Earnings earnings;
    @SerializedName("stats")
    @Expose
    private Stats stats;


    public Quote getQuote() {
        return quote;
    }

    public List<News> getNews() {
        return news;
    }

    public Company getCompany() {
        return company;
    }

    public Earnings getEarnings() {
        return earnings;
    }

    public Stats getStats() {
        return stats;
    }

    @Override
    public String getSymbol() {
        return quote.getSymbol();
    }

    public String getPlusOrMinors(){
        return quote.getChange() > 0 ? "+" : (quote.getChange() < 0 ? "-" : "");
    }

    public int getColor(){
        return quote.getChange() > 0 ? MyColor.GREEN : (quote.getChange() < 0 ? MyColor.RED : MyColor.GREY);
    }

    public String getUpOrDownArrow(){
        return quote.getChange() > 0 ? "▲" : (quote.getChange() < 0 ? "▼" : "");
    }
}

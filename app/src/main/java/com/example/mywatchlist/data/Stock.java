package com.example.mywatchlist.data;


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


    public Stock(Quote quote, List<News> news, Company company, Earnings earnings, Stats stats) {
        super();
        this.quote = quote;
        this.news = news;
        this.company = company;
        this.earnings = earnings;
        this.stats = stats;
    }

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
    public void display() {

    }
}

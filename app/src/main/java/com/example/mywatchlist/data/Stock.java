package com.example.mywatchlist.data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import static android.graphics.Color.rgb;

public class Stock implements StockData, Serializable{

    @SerializedName("quote")
    @Expose
    private Quote quote;
    @SerializedName("chart")
    @Expose
    private List<Chart> chart = null;
    @SerializedName("news")
    @Expose
    private List<News> news = null;
    @SerializedName("company")
    @Expose
    private Company company;
    @SerializedName("earnings")
    @Expose
    private Earnings earnings;

    /**
     * No args constructor for use in serialization
     *
     */
    public Stock() {
    }

    /**
     *
     * @param news
     * @param earnings
     * @param quote
     * @param company
     * @param chart
     */
    public Stock(Quote quote, List<Chart> chart, List<News> news, Company company, Earnings earnings) {
        super();
        this.quote = quote;
        this.chart = chart;
        this.news = news;
        this.company = company;
        this.earnings = earnings;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public List<Chart> getChart() {
        return chart;
    }

    public void setChart(List<Chart> chart) {
        this.chart = chart;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Earnings getEarnings() {
        return earnings;
    }

    public void setEarnings(Earnings earnings) {
        this.earnings = earnings;
    }

    @Override
    public void display() {

    }
}

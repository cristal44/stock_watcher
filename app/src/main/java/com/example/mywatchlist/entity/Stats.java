package com.example.mywatchlist.entity;

import com.example.mywatchlist.Utils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Stats implements Serializable {

    @SerializedName("week52change")
    @Expose
    private Double week52change;
    @SerializedName("week52high")
    @Expose
    private Double week52high;
    @SerializedName("week52low")
    @Expose
    private Double week52low;
    @SerializedName("marketcap")
    @Expose
    private float marketcap;
    @SerializedName("employees")
    @Expose
    private Integer employees;
    @SerializedName("day200MovingAvg")
    @Expose
    private Double day200MovingAvg;
    @SerializedName("day50MovingAvg")
    @Expose
    private Double day50MovingAvg;
    @SerializedName("float")
    @Expose
    private float _float;
    @SerializedName("avg10Volume")
    @Expose
    private Double avg10Volume;
    @SerializedName("avg30Volume")
    @Expose
    private Double avg30Volume;
    @SerializedName("ttmEPS")
    @Expose
    private Double ttmEPS;
    @SerializedName("ttmDividendRate")
    @Expose
    private Double ttmDividendRate;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("sharesOutstanding")
    @Expose
    private float sharesOutstanding;
    @SerializedName("maxChangePercent")
    @Expose
    private Double maxChangePercent;
    @SerializedName("year5ChangePercent")
    @Expose
    private Double year5ChangePercent;
    @SerializedName("year2ChangePercent")
    @Expose
    private Double year2ChangePercent;
    @SerializedName("year1ChangePercent")
    @Expose
    private Double year1ChangePercent;
    @SerializedName("ytdChangePercent")
    @Expose
    private Double ytdChangePercent;
    @SerializedName("month6ChangePercent")
    @Expose
    private Double month6ChangePercent;
    @SerializedName("month3ChangePercent")
    @Expose
    private Double month3ChangePercent;
    @SerializedName("month1ChangePercent")
    @Expose
    private Double month1ChangePercent;
    @SerializedName("day30ChangePercent")
    @Expose
    private Double day30ChangePercent;
    @SerializedName("day5ChangePercent")
    @Expose
    private Double day5ChangePercent;
    @SerializedName("nextDividendDate")
    @Expose
    private String nextDividendDate;
    @SerializedName("dividendYield")
    @Expose
    private Double dividendYield;
    @SerializedName("nextEarningsDate")
    @Expose
    private String nextEarningsDate;
    @SerializedName("exDividendDate")
    @Expose
    private String exDividendDate;
    @SerializedName("peRatio")
    @Expose
    private Double peRatio;
    @SerializedName("beta")
    @Expose
    private Double beta;

    public Double getWeek52change() {
        return week52change;
    }

    public Double getWeek52high() {
        return week52high;
    }

    public Double getWeek52low() {
        return week52low;
    }

    public Integer getEmployees() {
        return employees;
    }

    public Double getDay200MovingAvg() {
        return day200MovingAvg;
    }

    public Double getDay50MovingAvg() {
        return day50MovingAvg;
    }

    public float getFloat() {
        return _float;
    }

    public Double getAvg10Volume() {
        return avg10Volume;
    }

    public Double getAvg30Volume() {
        return avg30Volume;
    }

    public Double getTtmEPS() {
        return ttmEPS;
    }

    public Double getTtmDividendRate() {
        return ttmDividendRate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getSharesOutstanding() {
        return Utils.formatValue(sharesOutstanding);
    }

    public Double getMaxChangePercent() {
        return maxChangePercent;
    }

    public Double getYear5ChangePercent() {
        return year5ChangePercent;
    }

    public Double getYear2ChangePercent() {
        return year2ChangePercent;
    }

    public Double getYear1ChangePercent() {
        return year1ChangePercent;
    }

    public Double getYtdChangePercent() {
        return ytdChangePercent;
    }

    public Double getMonth6ChangePercent() {
        return month6ChangePercent;
    }

    public Double getMonth3ChangePercent() {
        return month3ChangePercent;
    }

    public Double getMonth1ChangePercent() {
        return month1ChangePercent;
    }

    public Double getDay30ChangePercent() {
        return day30ChangePercent;
    }

    public Double getDay5ChangePercent() {
        return day5ChangePercent;
    }

    public String getNextDividendDate() {
        return nextDividendDate;
    }

    public Double getDividendYield() {
        return dividendYield;
    }

    public String getNextEarningsDate() {
        return nextEarningsDate;
    }

    public String getExDividendDate() {
        return exDividendDate;
    }

    public Double getPeRatio() {
        return peRatio;
    }

    public Double getBeta() {
        return beta;
    }

}
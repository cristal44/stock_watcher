package com.example.mywatchlist.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class News implements Serializable {

    @SerializedName("datetime")
    @Expose
    private float datetime;
    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("related")
    @Expose
    private String related;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("hasPaywall")
    @Expose
    private Boolean hasPaywall;

    public float getDatetime() {
        return datetime;
    }

    public String getHeadline() {
        return headline;
    }

    public String getSource() {
        return source;
    }

    public String getUrl() {
        return url;
    }

    public String getSummary() {
        return summary;
    }

    public String getRelated() {
        return related;
    }

    public String getImage() {
        return image;
    }

    public String getLang() {
        return lang;
    }

    public Boolean getHasPaywall() {
        return hasPaywall;
    }

}

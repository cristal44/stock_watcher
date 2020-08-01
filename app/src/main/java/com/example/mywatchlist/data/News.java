package com.example.mywatchlist.data;

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

    /**
     * No args constructor for use in serialization
     *
     */
    public News() {
    }

    /**
     *
     * @param summary
     * @param image
     * @param datetime
     * @param related
     * @param source
     * @param lang
     * @param hasPaywall
     * @param headline
     * @param url
     */
    public News(float datetime, String headline, String source, String url, String summary, String related, String image, String lang, Boolean hasPaywall) {
        super();
        this.datetime = datetime;
        this.headline = headline;
        this.source = source;
        this.url = url;
        this.summary = summary;
        this.related = related;
        this.image = image;
        this.lang = lang;
        this.hasPaywall = hasPaywall;
    }

    public float getDatetime() {
        return datetime;
    }

    public void setDatetime(Integer datetime) {
        this.datetime = datetime;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Boolean getHasPaywall() {
        return hasPaywall;
    }

    public void setHasPaywall(Boolean hasPaywall) {
        this.hasPaywall = hasPaywall;
    }

}

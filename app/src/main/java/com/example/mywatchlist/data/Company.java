package com.example.mywatchlist.data;


import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Company implements Serializable {

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("exchange")
    @Expose
    private String exchange;
    @SerializedName("industry")
    @Expose
    private String industry;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("CEO")
    @Expose
    private String cEO;
    @SerializedName("securityName")
    @Expose
    private String securityName;
    @SerializedName("issueType")
    @Expose
    private String issueType;
    @SerializedName("sector")
    @Expose
    private String sector;
    @SerializedName("primarySicCode")
    @Expose
    private Integer primarySicCode;
    @SerializedName("employees")
    @Expose
    private Integer employees;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("address2")
    @Expose
    private Object address2;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("phone")
    @Expose
    private String phone;


    /**
     * @param zip
     * @param symbol
     * @param country
     * @param website
     * @param securityName
     * @param address
     * @param address2
     * @param city
     * @param primarySicCode
     * @param companyName
     * @param description
     * @param industry
     * @param cEO
     * @param tags
     * @param issueType
     * @param phone
     * @param exchange
     * @param state
     * @param employees
     * @param sector
     */
    public Company(String symbol, String companyName, String exchange, String industry, String website, String description, String cEO, String securityName, String issueType, String sector, Integer primarySicCode, Integer employees, List<String> tags, String address, Object address2, String state, String city, String zip, String country, String phone) {
        super();
        this.symbol = symbol;
        this.companyName = companyName;
        this.exchange = exchange;
        this.industry = industry;
        this.website = website;
        this.description = description;
        this.cEO = cEO;
        this.securityName = securityName;
        this.issueType = issueType;
        this.sector = sector;
        this.primarySicCode = primarySicCode;
        this.employees = employees;
        this.tags = tags;
        this.address = address;
        this.address2 = address2;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.country = country;
        this.phone = phone;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCEO() {
        return cEO;
    }

    public void setCEO(String cEO) {
        this.cEO = cEO;
    }

    public String getSecurityName() {
        return securityName;
    }

    public void setSecurityName(String securityName) {
        this.securityName = securityName;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Integer getPrimarySicCode() {
        return primarySicCode;
    }

    public void setPrimarySicCode(Integer primarySicCode) {
        this.primarySicCode = primarySicCode;
    }

    public Integer getEmployees() {
        return employees;
    }

    public void setEmployees(Integer employees) {
        this.employees = employees;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getAddress2() {
        return address2;
    }

    public void setAddress2(Object address2) {
        this.address2 = address2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

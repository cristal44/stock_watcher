package com.example.mywatchlist.data;

import retrofit2.Call;
import retrofit2.http.GET;


//https://api.iextrading.com/1.0/ref-data/symbols
public interface StockDataAPI {
    @GET("/symbols")
    Call<StockName> getSymbols();
}

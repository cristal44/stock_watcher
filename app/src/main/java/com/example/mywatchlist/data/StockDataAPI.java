package com.example.mywatchlist.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


//https://api.iextrading.com/1.0/ref-data/symbols
public interface StockDataAPI {
    @GET("ref-data/symbols")
    Call<List<StockName>> getStockSymbolNameList();


    @GET("stock/{symbol}/quote")
    Call<Quote> getQuote(@Path("symbol") String symbol, @Query("token") String token);

    @GET("stock/{symbol}/batch?types=quote,chart,news,company,earnings&period=annual")
    Call<Stock> getStock(@Path("symbol") String symbol, @Query("token") String token);
}

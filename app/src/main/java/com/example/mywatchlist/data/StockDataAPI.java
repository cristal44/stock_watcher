package com.example.mywatchlist.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


//https://api.iextrading.com/1.0/ref-data/symbols
//https://cloud.iexapis.com/stable/stock/twtr/chart/5d?token=pk_21c2a832de954d13b6ccdf397b29a341
public interface StockDataAPI {
    @GET("ref-data/symbols")
    Call<List<StockName>> getStockSymbolNameList();
//
//    @GET("stock/{symbol}/quote")
//    Call<Quote> getQuote(@Path("symbol") String symbol, @Query("token") String token);

    @GET("stock/{symbol}/batch?types=quote,chart,news,company,stats,earnings&period=annual")
    Call<Stock> getStock(@Path("symbol") String symbol, @Query("token") String token);


    @GET("stock/{symbol}/chart/{range}")
    Call<Charts> getChart(@Path("symbol") String symbol, @Path("range") String range, @Query("token") String token);


    @GET("stock/{symbol}/chart/today")
    Call<List<Data>> getTodayChart(@Path("symbol") String symbol, @Query("token") String token);
}

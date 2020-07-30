package com.example.mywatchlist.data;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StockClient {
    private static Retrofit symbolRetrofit = null;
    private static Retrofit stockRetrofit = null;

    public static Retrofit getSymbolRetrofit(){
        if (symbolRetrofit == null){
            symbolRetrofit = new Retrofit.Builder()
                    .baseUrl("https://api.iextrading.com/1.0/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return symbolRetrofit;
    }

    public static Retrofit getStockRetrofit(){
        if (stockRetrofit == null){
            stockRetrofit = new Retrofit.Builder()
                    .baseUrl("https://cloud.iexapis.com/stable/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return stockRetrofit;
    }
}

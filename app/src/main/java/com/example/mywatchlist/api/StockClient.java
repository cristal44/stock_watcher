package com.example.mywatchlist.api;

import java.io.IOException;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StockClient {

    private static Retrofit symbolRetrofit = null;
    private static Retrofit stockRetrofit = null;

    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    HttpUrl url = original.url().newBuilder()
                            .addQueryParameter("token", "Tsk_c0c5eca3abc64defb30295bb4ed704a7")
                            .build();

                    Request request = original.newBuilder().url(url).build();

                    return chain.proceed(request);
                }
            }).build();


    public static Retrofit getSymbolRetrofit(){
        if (symbolRetrofit == null){
            symbolRetrofit = new Retrofit.Builder()
                    .baseUrl(Api.SYMBOLURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }

        return symbolRetrofit;
    }

    public static Retrofit getStockRetrofit(){
        if (stockRetrofit == null){
            stockRetrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(httpClient)
                    .build();
        }

        return stockRetrofit;
    }
}

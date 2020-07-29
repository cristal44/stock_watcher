package com.example.mywatchlist.data;

import com.example.mywatchlist.View.MainActivity;
import com.example.mywatchlist.View.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager {
    private List<StockName> stockNameList;
    private MainActivity mainActivity;
    private List<Quote> quoteList = new ArrayList<>();

    public DataManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void getStockName() {
        Call<List<StockName>> stockNameCall = StockSymbolClient.getSymbolRetrofit().create(StockDataAPI.class).getStockSymbolNameList();

        stockNameCall.enqueue(new Callback<List<StockName>>() {
            @Override
            public void onResponse(Call<List<StockName>> call, Response<List<StockName>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                stockNameList = response.body();
                mainActivity.setStockNameList(stockNameList);
            }

            @Override
            public void onFailure(Call<List<StockName>> call, Throwable t) {

            }
        });
    }

    public void getStock(String symbol){
        Call<Quote> quoteCall = StockSymbolClient.getStockRetrofit().create(StockDataAPI.class).getQuote(symbol,"pk_21c2a832de954d13b6ccdf397b29a341");
        quoteCall.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                if (response.isSuccessful()){
                    System.out.println("1111111111111");
                    Quote quote= response.body();
                    mainActivity.setStockView(quote);
//                    quoteList.add(quote);
//                    mainActivity.setStockView(quoteList);
                }
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                t.printStackTrace();
                System.out.println("2222222222222");
            }
        });

    }
}

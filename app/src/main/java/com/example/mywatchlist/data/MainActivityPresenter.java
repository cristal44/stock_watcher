package com.example.mywatchlist.data;

import com.example.mywatchlist.View.BaseView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivityPresenter {
    private BaseView mainActivity;
    private static List<StockData> quoteList = new ArrayList<>();

    public MainActivityPresenter(BaseView mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void getStock(String symbol){
        Call<Quote> quoteCall = StockSymbolClient.getStockRetrofit().create(StockDataAPI.class).getQuote(symbol,"pk_21c2a832de954d13b6ccdf397b29a341");
        quoteCall.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                if (response.isSuccessful()){
                    Quote quote= response.body();
                    quoteList.add(quote);
                    mainActivity.updateData(quoteList);
                }
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
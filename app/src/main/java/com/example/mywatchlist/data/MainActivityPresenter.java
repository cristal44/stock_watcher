package com.example.mywatchlist.data;

import com.example.mywatchlist.View.BaseView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivityPresenter {
    private BaseView mainActivity;
    private static List<StockData> stocks = new ArrayList<>();

    public MainActivityPresenter(BaseView mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void getStockObject(String symbol) {
        Call<Stock> quoteCall = StockClient.getStockRetrofit().create(StockDataAPI.class).getStock(symbol, "Tsk_c0c5eca3abc64defb30295bb4ed704a7");
        quoteCall.enqueue(new Callback<Stock>() {
            @Override
            public void onResponse(Call<Stock> call, Response<Stock> response) {
                if (response.isSuccessful()) {

                    Stock stock = response.body();

                    for (int i = 0; i < stocks.size(); i++) {
                        Stock s = (Stock) stocks.get(i);
                        if (s.getQuote().getSymbol().equals(stock.getQuote().getSymbol())) {
                            mainActivity.display();
                            return;
                        }
                    }

                    stocks.add(stock);
                    mainActivity.updateData(stocks);
                }
            }

            @Override
            public void onFailure(Call<Stock> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
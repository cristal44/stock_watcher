package com.example.mywatchlist.data;

import com.example.mywatchlist.View.BaseView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewPresenter implements PresenterBase {

    private BaseView baseView;


    public SearchViewPresenter(BaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    public void getData() {

        Call<List<StockName>> stockNameCall = StockClient.getSymbolRetrofit().create(StockDataAPI.class).getStockSymbolNameList();

        stockNameCall.enqueue(new Callback<List<StockName>>() {
            @Override
            public void onResponse(Call<List<StockName>> call, Response<List<StockName>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<StockName> stockNameList = response.body();

                List<StockData> stockDataList = new ArrayList<>();
                for (StockName stockName : stockNameList){
                    stockDataList.add(stockName);
                }

                baseView.updateData(stockDataList);
            }

            @Override
            public void onFailure(Call<List<StockName>> call, Throwable t) {

            }
        });
    }
}

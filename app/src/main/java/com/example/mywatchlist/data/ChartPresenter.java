package com.example.mywatchlist.data;

import com.example.mywatchlist.ui.main.ChartFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartPresenter implements PresenterBase {

    private ChartFragment chartFragment;

    public ChartPresenter(ChartFragment chartFragment) {
        this.chartFragment = chartFragment;
    }

    @Override
    public void getData() {


    }


    public void getCharts(String symbol, String range) {

        Call<Charts> chartCall = StockClient.getStockRetrofit().create(StockDataAPI.class).getChart(symbol, range, "pk_21c2a832de954d13b6ccdf397b29a341");
        chartCall.enqueue(new Callback<Charts>() {
            @Override
            public void onResponse(Call<Charts> call, Response<Charts> response) {
                if (response.isSuccessful()) {
                    System.out.println("1111111111");
                    Charts charts = response.body();
                    chartFragment.getChart(charts);
                }
            }

            @Override
            public void onFailure(Call<Charts> call, Throwable t) {
                System.out.println("!2222222222222");
                t.printStackTrace();
            }
        });
    }

    public void getTodayData(String symbol){
        Call<List<Data>> todayChartCall = StockClient.getStockRetrofit().create(StockDataAPI.class).getTodayChart(symbol,  "pk_21c2a832de954d13b6ccdf397b29a341");
        todayChartCall.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if (response.isSuccessful()){
                    List<Data> data = response.body();
                    chartFragment.getTodayData(data);
                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

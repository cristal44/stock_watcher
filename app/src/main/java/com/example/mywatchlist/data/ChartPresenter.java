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

        Call<List<Data>> chartCall = StockClient.getStockRetrofit().create(StockDataAPI.class).getChart(symbol, range, "Tsk_c0c5eca3abc64defb30295bb4ed704a7");
        chartCall.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if (response.isSuccessful()) {
                    List<Data> charts = response.body();
                    List<StockData> stockDataList = new ArrayList<>();
                    for (Data data : charts){
                        stockDataList.add((StockData) data);
                    }
                    chartFragment.updateData(stockDataList);
                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getTodayData(String symbol){
        Call<Charts> todayChartCall = StockClient.getStockRetrofit().create(StockDataAPI.class).getTodayChart(symbol,  "Tsk_c0c5eca3abc64defb30295bb4ed704a7");
        todayChartCall.enqueue(new Callback<Charts>() {
            @Override
            public void onResponse(Call<Charts> call, Response<Charts> response) {
                if (response.isSuccessful()){
                    Charts charts = response.body();
                    List<StockData> stockDataList = new ArrayList<>();
                    for (Data data : charts.getData()){
                        stockDataList.add((StockData) data);
                    }
                    chartFragment.updateData(stockDataList);
                }
            }

            @Override
            public void onFailure(Call<Charts> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

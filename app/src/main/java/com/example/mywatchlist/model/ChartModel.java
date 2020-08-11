package com.example.mywatchlist.model;

import com.example.mywatchlist.api.StockClient;
import com.example.mywatchlist.api.StockDataAPI;
import com.example.mywatchlist.entity.Data;
import com.example.mywatchlist.entity.StockData;
import com.example.mywatchlist.presenter.OnFinish;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ChartModel implements IModel{
    private OnFinish onFinish;

    public ChartModel(OnFinish onFinish) {
        this.onFinish = onFinish;
    }

    @Override
    public void getData(String string) {
        String[] info = string.split(",");
        String symbol = info[0];
        String range = info[1];

        StockDataAPI stockDataAPI = StockClient.getStockRetrofit().create(StockDataAPI.class);
        Observable<List<Data>> getChartData = stockDataAPI.getChart(symbol,range);
        getChartData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Data>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Data> list) {
                        List<StockData> chartList = new ArrayList<>();
                        for (Data s : list){
                            chartList.add(s);
                        }
                        onFinish.onFinishListener(chartList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

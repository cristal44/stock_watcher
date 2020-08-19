package com.example.mywatchlist.model;

import com.example.mywatchlist.api.StockClient;
import com.example.mywatchlist.api.StockDataAPI;
import com.example.mywatchlist.entity.Quote;
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

public class ThreeIndexModel implements IModel {

    private OnFinish onFinish;

    public ThreeIndexModel(OnFinish onFinish) {
        this.onFinish = onFinish;
    }

    @Override
    public void getData(String symbol) {
        StockDataAPI stockDataAPI = StockClient.getStockRetrofit().create(StockDataAPI.class);
        Observable<Quote> getStockData = stockDataAPI.getQuotes(symbol);
        getStockData.subscribeOn(Schedulers.io())
                .retry(25)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Quote>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) { }

                    @Override
                    public void onNext(@NonNull Quote q) {
                        List<StockData> list = new ArrayList<>();
                        list.add(q);
                        onFinish.onFinishListener(list);
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

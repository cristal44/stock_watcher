package com.example.mywatchlist.model;

import com.example.mywatchlist.presenter.OnFinish;
import com.example.mywatchlist.api.StockClient;
import com.example.mywatchlist.entity.StockData;
import com.example.mywatchlist.api.StockDataAPI;
import com.example.mywatchlist.entity.StockSymbol;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchSymbolModel implements IModel {
    private OnFinish onFinish;

    public SearchSymbolModel(OnFinish onFinish) {
        this.onFinish = onFinish;
    }

    @Override
    public void getData(String string) {
        StockDataAPI stockDataAPI = StockClient.getSymbolRetrofit().create(StockDataAPI.class);

        Observable<List<StockSymbol>> getStockNameList = stockDataAPI.getStockSymbolNameList();
        getStockNameList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<StockSymbol>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<StockSymbol> stockSymbols) {
                        List<StockData> list = new ArrayList<>();

                        for (StockSymbol s : stockSymbols){
                            list.add(s);
                        }
                        onFinish.onFinishListener(list);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

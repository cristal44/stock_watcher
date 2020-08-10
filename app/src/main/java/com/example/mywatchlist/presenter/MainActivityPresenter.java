package com.example.mywatchlist.presenter;

import com.example.mywatchlist.View.BaseView;
import com.example.mywatchlist.entity.Stock;
import com.example.mywatchlist.entity.StockData;
import com.example.mywatchlist.model.IModel;
import com.example.mywatchlist.model.StockModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivityPresenter implements PresenterBase {
    private BaseView mainActivity;
    private IModel stockModel;
    private static List<StockData> stocks = new ArrayList<>();

    public MainActivityPresenter(BaseView mainActivity) {
        this.mainActivity = mainActivity;
        stockModel = new StockModel(this);
    }


    @Override
    public void getData(String symbol) {
        stockModel.getData(symbol);
    }

    @Override
    public void onFinishListener(List<StockData> list) {
        if (list != null) {
            if (list.size() == 1) {
                Stock stock = (Stock) list.get(0);

                for (int i = 0; i < stocks.size(); i++) {
                    Stock s = (Stock) stocks.get(i);
                    if (s.getQuote().getSymbol().equals((stock.getQuote().getSymbol()))) {
                        mainActivity.displayDialog();
                        return;
                    }
                }

                stocks.add(stock);
                mainActivity.success(stocks);

            }
        }
    }
}
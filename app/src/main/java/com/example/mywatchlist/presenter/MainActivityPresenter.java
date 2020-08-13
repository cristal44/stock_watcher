package com.example.mywatchlist.presenter;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.mywatchlist.View.BaseView;
import com.example.mywatchlist.entity.Stock;
import com.example.mywatchlist.entity.StockData;
import com.example.mywatchlist.model.IModel;
import com.example.mywatchlist.model.StockModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivityPresenter implements PresenterBase {
    private BaseView mainActivity;
    private IModel stockModel;
    private static List<StockData> stocks = new ArrayList<>();
    private static boolean isAllFirst = true;
    private int sizeBeforeRefresh;
    private String symbol;

    public MainActivityPresenter(BaseView mainActivity) {
        this.mainActivity = mainActivity;
        stockModel = new StockModel(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void getData(String symbol) {
        this.symbol = symbol;
        sizeBeforeRefresh = stocks.size();
        if (symbol.equals("all")) {
            if (isAllFirst) {
                stockModel.getData("AAPL");
                stockModel.getData("AMZN");
                stockModel.getData("TSLA");
                isAllFirst = false;
            }
        } else if (symbol.equals("refresh")) {
            List<String> symbolList = stocks.stream().map(s -> s.getSymbol()).collect(Collectors.toList());
            stocks.clear();
            symbolList.stream().forEach(s -> stockModel.getData(s));
        } else {
            stockModel.getData(symbol);
        }
    }


    @Override
    public void onFinishListener(List<StockData> list) {
        if (!list.isEmpty()) {
            StockData stockData = list.get(0);
            if (symbol.equals("all")) {
                stocks.add(stockData);
                if (stocks.size() == 3) {
                    mainActivity.display(stocks);
                }
            } else if (symbol.equals("refresh")) {
                stocks.add(stockData);
                if (stocks.size() == sizeBeforeRefresh) {
                    mainActivity.display(stocks);
                }
            } else {
                for (int i = 0; i < stocks.size(); i++) {
                    StockData s = stocks.get(i);
                    if (s.getSymbol().equals((stockData.getSymbol()))) {
                        mainActivity.display(stocks);
                        mainActivity.displayDialog();
                        return;
                    }
                }
                stocks.add(stockData);
                mainActivity.display(stocks);
            }
        }
    }

    public void updateList(Stock stock) {
       if (stocks.contains(stock)){
           stocks.remove(stock);
       } else {
           stocks.add(stock);
       }
    }
}
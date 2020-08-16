package com.example.mywatchlist.presenter;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.mywatchlist.View.MainActivity;
import com.example.mywatchlist.entity.Stock;
import com.example.mywatchlist.entity.StockData;
import com.example.mywatchlist.model.IModel;
import com.example.mywatchlist.model.StockModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivityPresenter implements PresenterBase {

    private MainActivity mainActivity;
    private IModel stockModel;
    private static List<StockData> stocks = new ArrayList<>();
    private static boolean isAllFirst = true;
    private int sizeBeforeRefresh;
    private String symbol;

    public MainActivityPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        stockModel = new StockModel(this);
    }

    @Override
    public void getData(String symbol) {
        this.symbol = symbol;
        sizeBeforeRefresh = stocks.size();
        if (symbol.equals("threeIndex")) {
            getDataForThreeIndex();
        } else if (symbol.equals("refresh")) {
            getDataForRefresh();
        } else {
            getDataForRequestSymbol(symbol);
        }
    }


    @Override
    public void onFinishListener(List<StockData> list) {
        if (!list.isEmpty()) {
            StockData stockData = list.get(0);

            if (symbol.equals("threeIndex")) {
                setDataForThreeIndex(stockData);
            } else if (symbol.equals("refresh")) {
                setDataForRefresh(stockData);
            } else {
                setDataForRequestSymbol(stockData);
            }
        }
    }


    public void updateList(Stock stock) {
        if (stocks.contains(stock)) {
            stocks.remove(stock);
        } else {
            stocks.add(stock);
        }
    }


    public void getDataForThreeIndex() {
        if (isAllFirst) {
            stockModel.getData("AAPL");
            stockModel.getData("AMZN");
            stockModel.getData("TSLA");
            isAllFirst = false;
        }
    }

    public void getDataForRefresh() {
        List<String> symbolList = stocks.stream().map(s -> s.getSymbol()).collect(Collectors.toList());
        stocks.clear();
        symbolList.stream().forEach(s -> stockModel.getData(s));
    }

    public void getDataForRequestSymbol(String symbol) {
        stockModel.getData(symbol);
    }

    private void setDataForRequestSymbol(StockData stockData) {
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

    private void setDataForRefresh(StockData stockData) {
        stocks.add(stockData);
        if (stocks.size() == sizeBeforeRefresh) {
            mainActivity.display(stocks);
        }
    }

    private void setDataForThreeIndex(StockData data) {
        stocks.add(data);

        if (stocks.size() == 3) {
            mainActivity.display(stocks);
        }
    }
}
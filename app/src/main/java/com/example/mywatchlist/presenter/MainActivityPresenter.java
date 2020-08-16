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
    private int sizeBeforeRefresh;
    private int threeIndex = 0;
    private boolean isRefresh = false;
    private boolean isSortedByName = true;
    private boolean isSortedByPrice = true;
    private boolean isSortedByChange = true;

    public MainActivityPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        stockModel = new StockModel(this);
    }

    public void getDataForIndex(){
        stockModel.getData("AAPL");
        stockModel.getData("AMZN");
        stockModel.getData("TSLA");
    }

    @Override
    public void getData(String symbol) {
        stockModel.getData(symbol);
    }

    public void refreshData(){
        isRefresh = true;
        threeIndex = 0;
        sizeBeforeRefresh = stocks.size();

        getDataForIndex();
        List<String> symbolList = stocks.stream().map(s -> s.getSymbol()).collect(Collectors.toList());
        stocks.clear();
        symbolList.stream().forEach(s -> stockModel.getData(s));
    }


    @Override
    public void onFinishListener(List<StockData> list) {
        StockData data = list.get(0);

        switch (data.getSymbol()) {
            case "AAPL":
                mainActivity.displayDJIA((Stock) data);
                threeIndex = isRefresh ? threeIndex+1 : 0;
                break;
            case "AMZN":
                mainActivity.displayNAS((Stock) data);
                threeIndex = isRefresh ? threeIndex+1 : 0;
                break;
            case "TSLA":
                mainActivity.displaySP((Stock) data);
                threeIndex = isRefresh ? threeIndex+1 : 0;
                break;
            default:
                setDataForRequestSymbol(data);
        }

        if  (isRefresh && sizeBeforeRefresh == 0 && threeIndex == 3){
            isRefresh = false;
            threeIndex = 0;
            mainActivity.stopRefresh();
        }
    }


    public void updateList(Stock stock) {
        if (stocks.contains(stock)) {
            stocks.remove(stock);
        } else {
            stocks.add(stock);
        }
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

    public void sortedStocksByName(){
        if (isSortedByName) {
            stocks.sort((StockData s1, StockData s2) -> s1.getSymbol().compareTo(s2.getSymbol()));
            isSortedByName = false;
            mainActivity.displayBasicViewSortedText("▲");
        } else {
            stocks.sort((StockData s1, StockData s2) -> s2.getSymbol().compareTo(s1.getSymbol()));
            isSortedByName = true;
            mainActivity.displayBasicViewSortedText("▼");

        }
        mainActivity.display(stocks);
    }

    public void sortedStocksByPrice() {
        List<Stock> stockList = convertToStockList();

        if (isSortedByPrice) {
            stockList.sort((Stock s1, Stock s2) -> Double.compare(s1.getQuote().getLatestPrice(),s2.getQuote().getLatestPrice()));
            isSortedByPrice = false;
            mainActivity.displayPriceSortedText("▲");
        } else {
            stockList.sort((Stock s1, Stock s2) -> Double.compare(s2.getQuote().getLatestPrice(),s1.getQuote().getLatestPrice()));
            isSortedByPrice = true;
            mainActivity.displayPriceSortedText("▼");

        }
        stocks.clear();
        stockList.forEach(s -> stocks.add(s));
        mainActivity.display(stocks);
    }



    public void sortedStocksByChange() {
        List<Stock> stockList = convertToStockList();

        if (isSortedByChange) {
            stockList.sort((Stock s1, Stock s2) -> Double.compare(s1.getQuote().getChange(),s2.getQuote().getChange()));
            isSortedByChange = false;
            mainActivity.displayChangeSortedText("▲");

        } else {
            stockList.sort((Stock s1, Stock s2) -> Double.compare(s2.getQuote().getChange(),s1.getQuote().getChange()));
            isSortedByChange = true;
            mainActivity.displayChangeSortedText("▼");

        }
        stocks.clear();
        stockList.forEach(s -> stocks.add(s));
        mainActivity.display(stocks);
    }

    public List<Stock> convertToStockList(){
        List<Stock> stockList = new ArrayList<>();
        stocks.forEach(a -> stockList.add((Stock) a));

        return stockList;
    }
}
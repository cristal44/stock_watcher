package com.example.mywatchlist.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.mywatchlist.View.MainActivity;
import com.example.mywatchlist.entity.Stock;
import com.example.mywatchlist.entity.StockData;
import com.example.mywatchlist.model.IModel;
import com.example.mywatchlist.model.StockModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivityPresenter implements PresenterBase {
    private MainActivity mainActivity;
    private IModel stockModel;
    private static List<StockData> stocks = new ArrayList<>();
    private List<StockData> threeIndexList = new ArrayList<>();
    private int sizeBeforeRefresh;
    private boolean isRefresh = false;
    private boolean isSortedByName = true;
    private boolean isSortedByPrice = true;
    private boolean isSortedByChange = true;
    private static boolean isFirst = true;
    private static ConnectivityManager connectivityManager;

    public MainActivityPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        stockModel = new StockModel(this);

        readFile();
    }

    public void getDataForIndex() {
        threeIndexList.clear();
        stockModel.getData("AAPL");
        stockModel.getData("AMZN");
        stockModel.getData("TSLA");
    }

    @Override
    public void getData(String symbol) {
        stockModel.getData(symbol);
    }

    public void refreshData() {
        isRefresh = true;
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
                threeIndexList.add(data);
                break;
            case "AMZN":
                mainActivity.displayNAS((Stock) data);
                threeIndexList.add(data);
                break;
            case "TSLA":
                mainActivity.displaySP((Stock) data);
                threeIndexList.add(data);
                break;
            default:
                setDataForRequestSymbol(data);
        }


        if (isRefresh && sizeBeforeRefresh == 0 && threeIndexList.size() == 3) {
            isRefresh = false;
            threeIndexList.clear();
            mainActivity.stopRefresh();
        }

        System.out.println(threeIndexList.size());
    }


    public void updateList(Stock stock) {
        if (stocks.contains(stock)) {
            stocks.remove(stock);
        } else {
            stocks.add(stock);
        }
        writeToFile();
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

        writeToFile();
        mainActivity.display(stocks);
    }

    public void sortedStocksByName() {
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
        writeToFile();
    }

    public void sortedStocksByPrice() {
        List<Stock> stockList = convertToStockList();

        if (isSortedByPrice) {
            stockList.sort((Stock s1, Stock s2) -> Double.compare(s1.getQuote().getLatestPrice(), s2.getQuote().getLatestPrice()));
            isSortedByPrice = false;
            mainActivity.displayPriceSortedText("▲");
        } else {
            stockList.sort((Stock s1, Stock s2) -> Double.compare(s2.getQuote().getLatestPrice(), s1.getQuote().getLatestPrice()));
            isSortedByPrice = true;
            mainActivity.displayPriceSortedText("▼");

        }
        stocks.clear();
        stockList.forEach(s -> stocks.add(s));
        mainActivity.display(stocks);
        writeToFile();
    }


    public void sortedStocksByChange() {
        List<Stock> stockList = convertToStockList();

        if (isSortedByChange) {
            stockList.sort((Stock s1, Stock s2) -> Double.compare(s1.getQuote().getChange(), s2.getQuote().getChange()));
            isSortedByChange = false;
            mainActivity.displayChangeSortedText("▲");

        } else {
            stockList.sort((Stock s1, Stock s2) -> Double.compare(s2.getQuote().getChange(), s1.getQuote().getChange()));
            isSortedByChange = true;
            mainActivity.displayChangeSortedText("▼");

        }
        stocks.clear();
        stockList.forEach(s -> stocks.add(s));
        mainActivity.display(stocks);
        writeToFile();
    }

    public List<Stock> convertToStockList() {
        List<Stock> stockList = new ArrayList<>();
        stocks.forEach(a -> stockList.add((Stock) a));

        return stockList;
    }

    public void writeToFile() {
        String stocksString = new Gson().toJson(stocks);
        try {
            FileOutputStream fileOutputStream = mainActivity.openFileOutput("stocks.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(stocksString.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        try {
            readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {

        if (isFirst) {
            try {
                String stocksResult = "";
                try {
                    StringBuffer stringBuffer = new StringBuffer();
                    FileInputStream fileInputStream = mainActivity.openFileInput("stocks.txt");
                    int ch;
                    while ((ch = fileInputStream.read()) != -1) {
                        stringBuffer.append((char) ch);
                    }
                    fileInputStream.close();
                    stocksResult = stringBuffer.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (!stocksResult.isEmpty()) {

                    Gson gson = new Gson();
                    Type empTypeList = new TypeToken<ArrayList<Stock>>() {
                    }.getType();

                    stocks.clear();
                    stocks = gson.fromJson(stocksResult, empTypeList);


                    if (!stocks.isEmpty()) {
                        if (isNetWorkConnected()) {
                            refreshData();
                        } else {
                            List<Stock> stockList = new ArrayList<>();
                            stocks.forEach(t -> stockList.add((Stock)t));
                            mainActivity.setStockList(stockList);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            isFirst = false;
        }

    }

    public boolean isNetWorkConnected(){
        if(connectivityManager == null) {
            connectivityManager =
                    (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }


//    private void noNetWorkDialog(String string) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
//        builder.setTitle("No Network Connection");
//        builder.setMessage(string);
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }


}
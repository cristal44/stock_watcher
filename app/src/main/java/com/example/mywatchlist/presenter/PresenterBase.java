package com.example.mywatchlist.presenter;

import com.example.mywatchlist.View.SearchActivity;
import com.example.mywatchlist.entity.StockData;
import com.example.mywatchlist.entity.StockSymbol;

import java.util.List;

public interface PresenterBase extends OnFinish {
    public void getData(String symbol);
}

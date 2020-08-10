package com.example.mywatchlist.View;

import com.example.mywatchlist.entity.StockData;

import java.util.List;

public interface BaseView {
    public void displayDialog();
    public void success(List<StockData> list);
}

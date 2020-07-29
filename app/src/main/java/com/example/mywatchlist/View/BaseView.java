package com.example.mywatchlist.View;

import com.example.mywatchlist.data.StockData;

import java.util.List;

public interface BaseView {
    public void display();
    public void updateData(List<StockData> list);
}

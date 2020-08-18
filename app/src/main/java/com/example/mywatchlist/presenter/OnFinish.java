package com.example.mywatchlist.presenter;

import com.example.mywatchlist.entity.StockData;
import java.util.List;

public interface OnFinish {
    void onFinishListener(List<StockData> list);
}

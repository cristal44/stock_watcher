package com.example.mywatchlist.presenter;

import com.example.mywatchlist.View.BaseView;
import com.example.mywatchlist.entity.Data;
import com.example.mywatchlist.entity.StockData;
import com.example.mywatchlist.model.ChartModel;
import com.example.mywatchlist.model.IModel;
import java.util.List;

public class ChartPresenter implements PresenterBase {
    private BaseView chartFragment;
    private IModel chartModel;
    private String range;

    public ChartPresenter(BaseView chartFragment) {
        this.chartFragment = chartFragment;
        chartModel = new ChartModel(this);
    }

    @Override
    public void getData(String symbol) {
        chartModel.getData(symbol);
        range = symbol.split(",")[1];
    }

    @Override
    public void onFinishListener(List<StockData> list) {
        for (StockData stockData : list) {
            Data data = (Data) stockData;
            data.setxValueString(getValue(data));
        }
        chartFragment.display(list);
    }

    public String getValue(Data data) {
        switch (range) {
            case "today":
                return data.getMinute();
            case "5dm":
            case "1mm":
                return data.getDate().split(" ")[1].split(",")[0];
            case "6m":
                return data.getDate().split(",")[0];
            case "1y":
                return data.getLabel().split(" ")[0];
            case "max":
                return data.getDate().split(",")[1];
            default:
                return null;
        }
    }
}
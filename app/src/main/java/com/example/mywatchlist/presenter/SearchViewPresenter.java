package com.example.mywatchlist.presenter;

import com.example.mywatchlist.View.BaseView;
import com.example.mywatchlist.model.IModel;
import com.example.mywatchlist.entity.StockData;
import com.example.mywatchlist.model.SearchSymbolModel;
import java.util.List;


public class SearchViewPresenter implements PresenterBase {
    private BaseView baseView;
    private IModel symbolModel;

    public SearchViewPresenter(BaseView baseView) {
        this.baseView = baseView;
        symbolModel = new SearchSymbolModel(this);
    }

    @Override
    public void getData(String string) {
        symbolModel.getData(string);
    }

    @Override
    public void onFinishListener(List<StockData> list) {
        baseView.display(list);
    }
}

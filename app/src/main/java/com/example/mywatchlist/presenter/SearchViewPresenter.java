package com.example.mywatchlist.presenter;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.mywatchlist.View.SearchActivity;
import com.example.mywatchlist.entity.StockSymbol;
import com.example.mywatchlist.model.IModel;
import com.example.mywatchlist.entity.StockData;
import com.example.mywatchlist.model.SearchSymbolModel;
import java.util.ArrayList;
import java.util.List;


public class SearchViewPresenter implements PresenterBase {
    private SearchActivity searchActivity;
    private IModel symbolModel;

    public SearchViewPresenter(SearchActivity searchActivity) {
        this.searchActivity = searchActivity;
        symbolModel = new SearchSymbolModel(this);
    }

    @Override
    public void getData(String string) {
        symbolModel.getData(string);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onFinishListener(List<StockData> list) {
        searchActivity.display(list);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<StockSymbol> getDisplayList(List<StockData> list){
        List<StockSymbol> stockSymbolList = new ArrayList<>();
        list.forEach(s -> stockSymbolList.add((StockSymbol) s));

        return stockSymbolList;
    }
}

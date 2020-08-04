package com.example.mywatchlist.View;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import com.example.mywatchlist.ListViewAdapter;
import com.example.mywatchlist.R;
import com.example.mywatchlist.data.PresenterBase;
import com.example.mywatchlist.data.SearchViewPresenter;
import com.example.mywatchlist.data.StockData;
import com.example.mywatchlist.data.StockName;
import java.util.ArrayList;
import java.util.List;
import static android.graphics.Color.rgb;

public class SearchActivity extends AppCompatActivity implements BaseView{
    private ListView listView;
    private ListViewAdapter adapter;
    private List<StockName> arrayList = new ArrayList<>();
    private SearchView searchView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        PresenterBase presenterBase = new SearchViewPresenter(this);
        presenterBase.getData();

        init();
        setToolbar();
        setSearchView();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar2);
        listView = findViewById(R.id.listView);
        searchView = findViewById(R.id.searchView);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(rgb(15,157,88), PorterDuff.Mode.SRC_ATOP);
//        toolbar.setNavigationIcon(R.drawable.ic_back);
    }

    private void setSearchView() {
        GradientDrawable backgroundGradient = (GradientDrawable) searchView.getBackground();
        backgroundGradient.setColor(rgb(255,255,255));

        searchView.onActionViewExpanded();
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)){
                    adapter.filter("");
                    listView.clearTextFilter();
                } else {
                    if (adapter != null) {
                        adapter.filter(newText);
                    }
                }
                return true;
            }
        });
    }


    @Override
    public void display() {

    }

    @Override
    public void updateData(List<StockData> list) {
        arrayList.clear();
        for (StockData stockName : list){
            arrayList.add((StockName) stockName);
        }

        adapter = new ListViewAdapter(this,arrayList);
        listView.setAdapter(adapter);
    }
}
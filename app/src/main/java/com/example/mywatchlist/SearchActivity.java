package com.example.mywatchlist;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import com.example.mywatchlist.data.StockName;
import java.util.ArrayList;
import static android.graphics.Color.rgb;

public class SearchActivity extends AppCompatActivity {
    private ListView listView;
    private ListViewAdapter adapter;
    String[] symbols;
    String[] names;
    ArrayList<StockName> arrayList = new ArrayList<>();
    private SearchView searchView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setToolbar();

        listView = findViewById(R.id.listView);

        sampleList();
        adapter = new ListViewAdapter(this,arrayList);
        listView.setAdapter(adapter);

        setSearchView();
    }

    private void sampleList() {
        symbols = new String[]{"AAPL", "PPLA", "CCTV"};
        names = new String[]{"Apple", "dddddddddddddd", "222222222"};
        for (int i = 0; i < symbols.length; i++){
            arrayList.add(new StockName(symbols[i], names[i]));
        }
    }

    private void setSearchView() {
        searchView = findViewById(R.id.searchView);
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
                    adapter.filter(newText);
                }
                return true;
            }
        });
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(rgb(15,157,88), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationIcon(R.drawable.ic_back);
    }

}
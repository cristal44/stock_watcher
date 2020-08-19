package com.example.mywatchlist.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import com.example.mywatchlist.ui.adapter.ListViewAdapter;
import com.example.mywatchlist.R;
import com.example.mywatchlist.presenter.SearchViewPresenter;
import com.example.mywatchlist.entity.StockData;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.rgb;

public class SearchActivity extends AppCompatActivity implements BaseView{

    @BindView(R.id.listView) ListView listView;
    @BindView(R.id.searchView) SearchView searchView;
    @BindView(R.id.toolbar2) Toolbar toolbar;

    private ListViewAdapter adapter;
    private SearchViewPresenter searchViewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        searchViewPresenter = new SearchViewPresenter(this);
        searchViewPresenter.getData("");

        setToolbar();
        setSearchView();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(rgb(15,157,88), PorterDuff.Mode.SRC_ATOP);
    }

    private void setSearchView() {
        GradientDrawable backgroundGradient = (GradientDrawable) searchView.getBackground();
        backgroundGradient.setColor(WHITE);

        searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void display(List<StockData> list) {
        adapter = new ListViewAdapter(this,searchViewPresenter.getDisplayList(list));
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (searchViewPresenter != null){
            searchViewPresenter = null;
        }
    }
}
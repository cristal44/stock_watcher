package com.example.mywatchlist.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.mywatchlist.R;
import com.example.mywatchlist.presenter.MainActivityPresenter;
import com.example.mywatchlist.entity.Stock;
import com.example.mywatchlist.entity.StockData;
import com.example.mywatchlist.ui.adapter.WatchlistAdapter;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, BaseView{

    @BindView(R.id.stockRecyclerView) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    private WatchlistAdapter watchlistAdapter;
    private static List<Stock> stockList = new ArrayList<>();
    private MainActivityPresenter mainActivityPresenter;
    private String selectedSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mainActivityPresenter = new MainActivityPresenter(this);

        setRecyclerView();
        setToolBar();
        getSelectedSymbol();
    }

    private void getSelectedSymbol() {
        // Get selectedStock from SearchActivity
        Intent intent = getIntent();
        selectedSymbol = intent.getStringExtra("SELECTEDSYMBOL");
        if (selectedSymbol != null) {
            mainActivityPresenter.getData(selectedSymbol);
        }
    }

    private void setToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setTitle("My Watchlist");
        toolbar.setTitleTextAppearance(this, R.style.CustomText);
    }

    private void setRecyclerView() {
        watchlistAdapter = new WatchlistAdapter(stockList, this);
        recyclerView.setAdapter(watchlistAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onClick(View v) {
        final int position = recyclerView.getChildLayoutPosition(v);
        final Stock stock = stockList.get(position);

        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("SELECTEDSTOCK", stock);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addStock) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void success(List<StockData> list) {
                stockList.clear();
        for (StockData data : list){
            stockList.add((Stock) data);
        }
        watchlistAdapter.notifyDataSetChanged();

    }

    @Override
    public void displayDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Duplicate Symbol")
                .setMessage(selectedSymbol + " has been in the Watchlist")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mainActivityPresenter != null){
            mainActivityPresenter = null;
        }
    }
}
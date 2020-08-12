package com.example.mywatchlist.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.mywatchlist.R;
import com.example.mywatchlist.SwipeHelper;
import com.example.mywatchlist.presenter.MainActivityPresenter;
import com.example.mywatchlist.entity.Stock;
import com.example.mywatchlist.entity.StockData;
import com.example.mywatchlist.ui.adapter.WatchlistAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BaseView, SwipeRefreshLayout.OnRefreshListener, WatchlistAdapter.OnStockListener {

    @BindView(R.id.stockRecyclerView) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.swiper) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.dowSymbol) TextView dowSymbol;
    @BindView(R.id.dowPrice) TextView dowPrice;
    @BindView(R.id.dowUpOrDown) TextView dowUpOrDown;
    @BindView(R.id.dowChange) TextView dowChange;
    @BindView(R.id.dowPercent) TextView dowPercent;
    @BindView(R.id.naSymbol) TextView naSymbol;
    @BindView(R.id.naPriceText) TextView naPrice;
    @BindView(R.id.naUpOrDown) TextView naUpOrDown;
    @BindView(R.id.naChange) TextView naChange;
    @BindView(R.id.naPercent) TextView naPercent;
    @BindView(R.id.spSymbol) TextView spSymbol;
    @BindView(R.id.spPrice) TextView spPrice;
    @BindView(R.id.spUpOrDown) TextView spUpOrDown;
    @BindView(R.id.spChange) TextView spChange;
    @BindView(R.id.spPercent) TextView spPercent;

    private WatchlistAdapter watchlistAdapter;
    private static List<Stock> stockList = new ArrayList<>();
    private MainActivityPresenter mainActivityPresenter;
    private String selectedSymbol;
    public static List<String> refreshSymbol = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mainActivityPresenter = new MainActivityPresenter(this);

        mainActivityPresenter.getData("all");

        setRecyclerView();
        setToolBar();
        getSelectedSymbol();
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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


        ItemTouchHelper.Callback callback = new SwipeHelper(watchlistAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        watchlistAdapter.setItemTouchHelper(itemTouchHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(watchlistAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }


    @Override
    public void onStockClick(int position) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("SELECTEDSTOCK", stockList.get(position));
        startActivity(intent);
    }

    private void deleteNote(Stock stock){
        stockList.remove(stock);
        watchlistAdapter.notifyDataSetChanged();
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
    public void display(List<StockData> list) {
        stockList.clear();

        for (StockData data : list) {
            switch (data.getSymbol()){
                case "AAPL":
                    displayDJIA((Stock)data);
                    break;
                case "AMZN":
                    displayNAS((Stock)data);
                    break;
                case "TSLA":
                    displaySP((Stock)data);
                    break;
                default:
                    stockList.add((Stock) data);
                    watchlistAdapter.notifyDataSetChanged();
            }
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    public void displayDJIA(Stock stock) {

        dowSymbol.setText(stock.getSymbol());
        dowPrice.setText(String.format("%.2f", stock.getQuote().getLatestPrice()));

        dowUpOrDown.setText(stock.getQuote().getUpOrDownArrow());
        dowUpOrDown.setTextColor(stock.getQuote().getColor());

        dowChange.setText(String.format("%.2f", stock.getQuote().getChange()));
        dowChange.setTextColor(stock.getQuote().getColor());

        dowPercent.setText(String.format("(%.2f%%)", stock.getQuote().getChangePercent() * 100));
        dowPercent.setTextColor(stock.getQuote().getColor());
    }

    public void displayNAS(Stock stock) {
        int color = stock.getQuote().getColor();

        naSymbol.setText(stock.getSymbol());
        naPrice.setText(String.format("%.2f", stock.getQuote().getLatestPrice()));

        naUpOrDown.setText(stock.getQuote().getUpOrDownArrow());
        naUpOrDown.setTextColor(color);

        naChange.setText(String.format("%.2f", stock.getQuote().getChange()));
        naChange.setTextColor(color);

        naPercent.setText(String.format("(%.2f%%)", stock.getQuote().getChangePercent() * 100));
        naPercent.setTextColor(color);
    }

    public void displaySP(Stock stock) {
        int color = stock.getQuote().getColor();

        spSymbol.setText(stock.getSymbol());
        spPrice.setText(String.format("%.2f", stock.getQuote().getLatestPrice()));

        spUpOrDown.setText(stock.getQuote().getUpOrDownArrow());
        spUpOrDown.setTextColor(color);

        spChange.setText(String.format("%.2f", stock.getQuote().getChange()));
        spChange.setTextColor(color);

        spPercent.setText(String.format("(%.2f%%)", stock.getQuote().getChangePercent() * 100));
        spPercent.setTextColor(color);
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
        if (mainActivityPresenter != null) {
            mainActivityPresenter = null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onRefresh() {
        mainActivityPresenter.getData("refresh");
    }

}
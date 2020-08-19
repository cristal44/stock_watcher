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
import android.view.View;
import android.widget.TextView;
import com.example.mywatchlist.R;
import com.example.mywatchlist.entity.Quote;
import com.example.mywatchlist.ui.adapter.SwipeHelper;
import com.example.mywatchlist.presenter.MainActivityPresenter;
import com.example.mywatchlist.entity.Stock;
import com.example.mywatchlist.entity.StockData;
import com.example.mywatchlist.ui.adapter.WatchlistAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity implements BaseView, SwipeRefreshLayout.OnRefreshListener, WatchlistAdapter.OnStockListener{
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
    @BindView(R.id.basicViewSort) TextView basicViewSort;
    @BindView(R.id.priceSort) TextView priceSorted;
    @BindView(R.id.changeSort) TextView changeSorted;

    private WatchlistAdapter watchlistAdapter;
    private MainActivityPresenter mainActivityPresenter;
    private String selectedSymbol;
    private static List<Stock> stockList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mainActivityPresenter = new MainActivityPresenter(this);

        setRecyclerView();
        setToolBar();
        getSelectedSymbol();
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public void onSymbolSort(View view){
        mainActivityPresenter.sortedStocksByName();
    }

    public void displayBasicViewSortedText(String icon) {
        basicViewSort.setText("A-Z " + icon);
    }

    public void onPriceSort(View view){
        mainActivityPresenter.sortedStocksByPrice();
    }


    public void displayPriceSortedText(String icon) {
        priceSorted.setText("Price " + icon);
    }

    public void onChangeSort(View view) {
        mainActivityPresenter.sortedStocksByChange();
    }

    public void displayChangeSortedText(String icon) {
        changeSorted.setText("% change " + icon);
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
        watchlistAdapter = new WatchlistAdapter(stockList, this, this);

        ItemTouchHelper.Callback callback = new SwipeHelper(watchlistAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        watchlistAdapter.setItemTouchHelper(itemTouchHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(watchlistAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void stockListChanged(Stock stock) {
        mainActivityPresenter.updateList(stock);
    }

    @Override
    public void onStockClick(int position) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("SELECTEDSTOCK", stockList.get(position));
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
    public void display(List<StockData> list) {
        stockList.clear();
        for (StockData data : list) {
            stockList.add((Stock) data);
            watchlistAdapter.notifyDataSetChanged();
        }
        stopRefresh();
    }

    public void displayThreeIndices(Quote dia, Quote nas, Quote sp){
        displayIndex(dia,dowSymbol,dowPrice,dowUpOrDown,dowChange,dowPercent);
        displayIndex(nas,naSymbol,naPrice,naUpOrDown,naChange,naPercent);
        displayIndex(sp,spSymbol,spPrice,spUpOrDown,spChange,spPercent);
    }

    public void displayIndex(Quote quote, TextView symbol, TextView price, TextView upOrDown, TextView change, TextView percentage) {
        int color = quote.getColor();

        symbol.setText(quote.getSymbol());
        price.setText(String.format("%.2f", quote.getLatestPrice()));

        upOrDown.setText(quote.getUpOrDownArrow());
        upOrDown.setTextColor(color);

        change.setText(String.format("%.2f", quote.getChange()));
        change.setTextColor(color);

        percentage.setText(String.format("(%.2f%%)", quote.getChangePercent() * 100));
        percentage.setTextColor(color);
    }

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
        mainActivityPresenter.refreshData();
    }

    public void stopRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    public void setStockList(List<Stock> stocks) {
        this.stockList = stocks;
    }
}
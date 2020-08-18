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
    @BindView(R.id.basicViewSort) TextView basicViewSort;
    @BindView(R.id.priceSort) TextView priceSorted;
    @BindView(R.id.changeSort) TextView changeSorted;

    private WatchlistAdapter watchlistAdapter;
    private MainActivityPresenter mainActivityPresenter;
    private String selectedSymbol;
    private List<Stock> stockList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mainActivityPresenter = new MainActivityPresenter(this);
        mainActivityPresenter.getDataForIndex();

        setRecyclerView();
        setToolBar();
        getSelectedSymbol();
        swipeRefreshLayout.setOnRefreshListener(this);
        sortedByName();
        sortedByPrice();
        sortedByChange();
    }

    public void sortedByName() {
        basicViewSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityPresenter.sortedStocksByName();
            }
        });
    }

    public void displayBasicViewSortedText(String icon) {
        basicViewSort.setText("A-Z " + icon);
    }

    public void sortedByPrice() {
        priceSorted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityPresenter.sortedStocksByPrice();
            }
        });
    }

    public void displayPriceSortedText(String icon) {
        priceSorted.setText("Price " + icon);
    }

    public void sortedByChange() {
        changeSorted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityPresenter.sortedStocksByChange();
            }
        });
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


    public void displayDJIA(Stock stock) {
        int color = stock.getQuote().getColor();

        dowSymbol.setText(stock.getSymbol());
        dowPrice.setText(String.format("%.2f", stock.getQuote().getLatestPrice()));

        dowUpOrDown.setText(stock.getQuote().getUpOrDownArrow());
        dowUpOrDown.setTextColor(color);

        dowChange.setText(String.format("%.2f", stock.getQuote().getChange()));
        dowChange.setTextColor(color);

        dowPercent.setText(String.format("(%.2f%%)", stock.getQuote().getChangePercent() * 100));
        dowPercent.setTextColor(color);
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
        mainActivityPresenter.refreshData();
    }

    public void stopRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    public void setStockList(List<Stock> stocks) {
        this.stockList = stocks;
    }
}
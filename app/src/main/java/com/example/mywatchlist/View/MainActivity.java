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
import com.example.mywatchlist.MyAdapter;
import com.example.mywatchlist.R;
import com.example.mywatchlist.data.MainActivityPresenter;
import com.example.mywatchlist.data.Stock;
import com.example.mywatchlist.data.StockData;
import com.example.mywatchlist.data.StockName;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, BaseView{
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private static List<Stock> stockList = new ArrayList<>();
    private MainActivityPresenter mainActivityPresenter;
    private String selectedSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityPresenter = new MainActivityPresenter(this);

        recyclerView = findViewById(R.id.stockRecyclerView);

        myAdapter = new MyAdapter(stockList, this);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setTitle("My Watchlist");
        toolbar.setTitleTextAppearance(this, R.style.CustomText);

        Intent intent = getIntent();
        StockName stockName = (StockName) intent.getSerializableExtra("SELECTED");
        if (stockName != null) {
            selectedSymbol = stockName.getSymbol();
            mainActivityPresenter.getStockObject(selectedSymbol);
        }
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
    public void updateData(List<StockData> list) {
        stockList.clear();
        for (StockData data : list){
            stockList.add((Stock) data);
        }
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void display() {
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

    public void displayAlertDialog(){


    }
}
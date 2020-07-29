package com.example.mywatchlist.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.mywatchlist.MyAdapter;
import com.example.mywatchlist.R;
import com.example.mywatchlist.data.DataManager;
import com.example.mywatchlist.data.Quote;
import com.example.mywatchlist.data.Stock;
import com.example.mywatchlist.data.StockName;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
//    private List<Stock> stockList;
    private List<Quote> quoteList = new ArrayList<>();
    private DataManager dataManager;
    static  List<StockName> stockNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        stockList = getStockList();

        dataManager = new DataManager(this);
        dataManager.getStockName();

        recyclerView = findViewById(R.id.stockRecyclerView);

        myAdapter = new MyAdapter(quoteList,this);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setTitle("My Watchlist");
        toolbar.setTitleTextAppearance(this,R.style.CustomText);

        Intent intent = getIntent();
        StockName stockName = (StockName) intent.getSerializableExtra("SELECTED");
        if (stockName != null) {
             String selectedSymbol = stockName.getSymbol();
//             System.out.println(selectedSymbol);
            dataManager.getStock(selectedSymbol);
        }
    }

//
//    public List<Stock> getStockList(){
//        List<Stock> stocks = new ArrayList<>();
//        stocks.add(new Stock("AAPL", 312.54,"Apple cccccccccccccccccc",2.74,0.03));
//        stocks.add(new Stock("AAPL", 312.54,"Apple",2.74,0.03));
//        stocks.add(new Stock("AAPL", 312.54,"Apple",2.74,0.03));
//        stocks.add(new Stock("AAPL", 312.54,"Apple",-1.25,0.03));
//        stocks.add(new Stock("AAPL", 312.54,"Apple",0,0.03));
//        stocks.add(new Stock("AAPL", 312.54,"Apple",2.74,0.03));
//
//        return stocks;
//    }

    @Override
    public void onClick(View v) {
        final int position = recyclerView.getChildLayoutPosition(v);
        final Quote stock = quoteList.get(position);

        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addStock){

            Toast.makeText(this,"IIIII",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setStockNameList(List<StockName> stockNameList) {
        stockNames = stockNameList;
    }

    public void setStockView(Quote quote) {
        quoteList.add(quote);
        quoteList.add(quote);
        myAdapter.notifyDataSetChanged();

    }
}
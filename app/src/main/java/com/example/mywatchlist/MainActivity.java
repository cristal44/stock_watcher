package com.example.mywatchlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.stockRecyclerView);
        myAdapter = new MyAdapter(getStockList());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }


    public List<Stock> getStockList(){
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("AAPL", 312.54,"Apple cccccccccccccccccc",2.74,0.03));
        stocks.add(new Stock("AAPL", 312.54,"Apple",2.74,0.03));
        stocks.add(new Stock("AAPL", 312.54,"Apple",2.74,0.03));
        stocks.add(new Stock("AAPL", 312.54,"Apple",-1.25,0.03));
        stocks.add(new Stock("AAPL", 312.54,"Apple",0,0.03));
        stocks.add(new Stock("AAPL", 312.54,"Apple",2.74,0.03));

        return stocks;
    }
}
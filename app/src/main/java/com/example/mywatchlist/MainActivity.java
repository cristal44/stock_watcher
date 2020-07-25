package com.example.mywatchlist;

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

import com.example.mywatchlist.data.Stock;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<Stock> stockList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stockList = getStockList();

        recyclerView = findViewById(R.id.stockRecyclerView);
        myAdapter = new MyAdapter(stockList,this);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


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

    @Override
    public void onClick(View v) {
        final int position = recyclerView.getChildLayoutPosition(v);
        final Stock stock = stockList.get(position);

        Intent intent = new Intent(this,DetailsActivity.class);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
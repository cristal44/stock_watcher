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
import com.example.mywatchlist.MyAdapter;
import com.example.mywatchlist.R;
import com.example.mywatchlist.data.MainActivityPresenter;
import com.example.mywatchlist.data.Quote;
import com.example.mywatchlist.data.StockData;
import com.example.mywatchlist.data.StockName;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, BaseView{
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<Quote> quoteList = new ArrayList<>();
    private MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityPresenter = new MainActivityPresenter(this);

        recyclerView = findViewById(R.id.stockRecyclerView);

        myAdapter = new MyAdapter(quoteList, this);
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
            String selectedSymbol = stockName.getSymbol();
            mainActivityPresenter.getStock(selectedSymbol);
        }
    }


    @Override
    public void onClick(View v) {
        final int position = recyclerView.getChildLayoutPosition(v);
        final Quote stock = quoteList.get(position);

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
        quoteList.clear();
        for (StockData data : list){
            quoteList.add((Quote) data);
        }
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void display() {

    }
}
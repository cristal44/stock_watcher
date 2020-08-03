package com.example.mywatchlist.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import com.example.mywatchlist.R;
import com.example.mywatchlist.data.Stock;
import com.example.mywatchlist.data.StockData;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.mywatchlist.ui.main.SectionsPagerAdapter;
import java.util.List;
import static android.graphics.Color.rgb;

public class DetailsActivity extends AppCompatActivity implements BaseView{
    private TabLayout tabs;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TextView title;
    private Stock stock;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        stock = (Stock) intent.getSerializableExtra("SELECTEDSTOCK");

        init();
        setTabDivider();
        setupToolbar();

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),this,3,stock);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }

    public void init(){
        tabs = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);
        toolbar = (Toolbar)findViewById(R.id.detailToolbar);
        title = findViewById(R.id.title);
    }

    public void setTabDivider(){
        View root = tabs.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(rgb(171,171,171));
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }
    }

    public void setupToolbar(){
        title.setText(stock.getQuote().getSymbol());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(rgb(15,157,88), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationIcon(R.drawable.ic_back);
    }

    @Override
    public void display() {

    }

    @Override
    public void updateData(List<StockData> list) {

    }

    public Stock getData(){
        return stock;
    }
}
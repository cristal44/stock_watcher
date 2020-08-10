package com.example.mywatchlist.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import com.example.mywatchlist.MyColor;
import com.example.mywatchlist.ui.adapter.MyViewPager;
import com.example.mywatchlist.R;
import com.example.mywatchlist.entity.Stock;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.mywatchlist.ui.adapter.SectionsPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.tabs) TabLayout tabs;
    @BindView(R.id.view_pager) MyViewPager viewPager;
    @BindView(R.id.detailToolbar) Toolbar toolbar;
    @BindView(R.id.title) TextView title;

    private Stock stock;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        inti();
        getStockFromMainActivity();
        setTabDivider();
        setupToolbar();
    }

    private void inti() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),this,3,stock);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setPagingEnabled(false);
        tabs.setupWithViewPager(viewPager);
    }

    public void getStockFromMainActivity(){
        Intent intent = getIntent();
        stock = (Stock) intent.getSerializableExtra("SELECTEDSTOCK");
    }

    public void setTabDivider(){
        View root = tabs.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(MyColor.GREY);
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
        toolbar.getNavigationIcon().setColorFilter(MyColor.GREEN, PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationIcon(R.drawable.ic_back);
    }

    public Stock getData(){
        return stock;
    }
}
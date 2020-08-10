package com.example.mywatchlist.ui.adapter;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.mywatchlist.R;
import com.example.mywatchlist.View.ChartFragment;
import com.example.mywatchlist.View.NewsFragment;
import com.example.mywatchlist.View.OverviewFragment;
import com.example.mywatchlist.entity.StockData;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;
    private int numberOfTabs;
    private StockData stock;


    public SectionsPagerAdapter(FragmentManager fm, Context mContext, int numberOfTabs, StockData stock) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.mContext = mContext;
        this.stock = stock;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                OverviewFragment overview = new OverviewFragment();
                return overview;
            case 1:
                ChartFragment chart = new ChartFragment();
                return chart;
            case 2:
                NewsFragment news = new NewsFragment();
                return news;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
}
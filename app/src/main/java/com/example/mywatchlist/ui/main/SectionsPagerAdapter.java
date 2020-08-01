package com.example.mywatchlist.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mywatchlist.R;
import com.example.mywatchlist.data.StockData;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
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
        // Show 2 total pages.
        return numberOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
}
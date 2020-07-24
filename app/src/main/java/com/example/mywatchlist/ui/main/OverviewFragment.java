package com.example.mywatchlist.ui.main;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.mywatchlist.CompetitorAdapter;
import com.example.mywatchlist.MyAdapter;
import com.example.mywatchlist.R;
import com.example.mywatchlist.Stock;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.rgb;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OverviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OverviewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OverviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OverviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OverviewFragment newInstance(String param1, String param2) {
        OverviewFragment fragment = new OverviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_overview, container, false);

        RelativeLayout overviewLayout = fragmentView.findViewById(R.id.overviewLayout);
        GradientDrawable backgroundGradient = (GradientDrawable) overviewLayout.getBackground();
        backgroundGradient.setColor(rgb(255,255,255));

        LinearLayout keyLayout = fragmentView.findViewById(R.id.keyLayout);
        GradientDrawable backgroundGradient1 = (GradientDrawable) keyLayout.getBackground();
        backgroundGradient1.setColor(rgb(255,255,255));

        GridLayout earningLayout = fragmentView.findViewById(R.id.earningGridLayout);
        GradientDrawable backgroundGradient2 = (GradientDrawable) earningLayout.getBackground();
        backgroundGradient2.setColor(rgb(255,255,255));

        LinearLayout companyProfileLayout = fragmentView.findViewById(R.id.companyProfileLayout);
        GradientDrawable backgroundGradient3 = (GradientDrawable) companyProfileLayout.getBackground();
        backgroundGradient3.setColor(rgb(255,255,255));


        RelativeLayout competitorLayout = fragmentView.findViewById(R.id.competitorLayout);
        GradientDrawable backgroundGradient4 = (GradientDrawable) competitorLayout.getBackground();
        backgroundGradient4.setColor(rgb(255,255,255));


        List<Stock> competitorList = getStockList();
        RecyclerView recyclerView = fragmentView.findViewById(R.id.competitorRecyclerView);
        CompetitorAdapter competitorAdapter = new CompetitorAdapter(competitorList);
        recyclerView.setAdapter(competitorAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        // Inflate the layout for this fragment
        return fragmentView;
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
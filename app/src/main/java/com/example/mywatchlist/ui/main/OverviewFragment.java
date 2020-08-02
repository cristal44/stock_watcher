package com.example.mywatchlist.ui.main;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mywatchlist.R;
import com.example.mywatchlist.View.DetailsActivity;
import com.example.mywatchlist.data.Stock;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static android.graphics.Color.rgb;

public class OverviewFragment extends Fragment {


    private Stock stock;
    private TextView companyName;
    private TextView classType;
    private TextView price;
    private TextView change;
    private TextView changePercent;
    private TextView upOrDownArrow;
    private TextView time;
    private TextView open;
    private TextView previousClose;
    private TextView dayRange;
    private TextView volume;
    private TextView averageVolume;
    private TextView last5dayChange;
    private TextView last3MonthChange;
    private TextView last1YearChange;
    private TextView week52Range;
    private TextView marketCap;
    private TextView peRatio;
    private TextView earningPerShare;
    private TextView yield;
    private TextView upEarningDate;
    private TextView upEarningEstimate;
    private TextView upEarningEstimateRange;
    private TextView laEarningActual;
    private TextView laEarningEstimate;
    private TextView laEarning5D;
    private TextView sector;
    private TextView industry;
    private TextView style;
    private TextView employees;
    private TextView ceo;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DetailsActivity detailsActivity = (DetailsActivity) getActivity();
        stock = detailsActivity.getData();
        display();
    }

    public OverviewFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_overview, container, false);

        init(fragmentView);

//
        RelativeLayout overviewLayout = fragmentView.findViewById(R.id.overviewLayout);
        GradientDrawable backgroundGradient = (GradientDrawable) overviewLayout.getBackground();
        backgroundGradient.setColor(rgb(255, 255, 255));

        LinearLayout keyLayout = fragmentView.findViewById(R.id.keyLayout);
        GradientDrawable backgroundGradient1 = (GradientDrawable) keyLayout.getBackground();
        backgroundGradient1.setColor(rgb(255, 255, 255));

        GridLayout earningLayout = fragmentView.findViewById(R.id.earningGridLayout);
        GradientDrawable backgroundGradient2 = (GradientDrawable) earningLayout.getBackground();
        backgroundGradient2.setColor(rgb(255, 255, 255));

        LinearLayout companyProfileLayout = fragmentView.findViewById(R.id.companyProfileLayout);
        GradientDrawable backgroundGradient3 = (GradientDrawable) companyProfileLayout.getBackground();
        backgroundGradient3.setColor(rgb(255, 255, 255));


//        RelativeLayout competitorLayout = fragmentView.findViewById(R.id.competitorLayout);
//        GradientDrawable backgroundGradient4 = (GradientDrawable) competitorLayout.getBackground();
//        backgroundGradient4.setColor(rgb(255,255,255));


//        List<Stock> competitorList = getStockList();
//        RecyclerView recyclerView = fragmentView.findViewById(R.id.competitorRecyclerView);
//        CompetitorAdapter competitorAdapter = new CompetitorAdapter(competitorList);
//        recyclerView.setAdapter(competitorAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        // Inflate the layout for this fragment
        return fragmentView;
    }


    public void init(View fragmentView) {
        companyName = fragmentView.findViewById(R.id.overviewCompanyName);
        price = fragmentView.findViewById(R.id.overviewPrice);
        change = fragmentView.findViewById(R.id.overviewChange);
        changePercent = fragmentView.findViewById(R.id.overviewPercentage);
        upOrDownArrow = fragmentView.findViewById(R.id.overviewArrow);
        time = fragmentView.findViewById(R.id.overviewDate);
        open = fragmentView.findViewById(R.id.open);
        previousClose = fragmentView.findViewById(R.id.previousClose);
        dayRange = fragmentView.findViewById(R.id.dayRange);
        volume = fragmentView.findViewById(R.id.volume);
        averageVolume = fragmentView.findViewById(R.id.averageVolume);
        last5dayChange = fragmentView.findViewById(R.id.day5Change);
        last3MonthChange = fragmentView.findViewById(R.id.mo3Change);
        last1YearChange = fragmentView.findViewById(R.id.yearChange);
        week52Range = fragmentView.findViewById(R.id.week52Range);
        marketCap = fragmentView.findViewById(R.id.marketCap);
        peRatio = fragmentView.findViewById(R.id.peRatio);
        earningPerShare = fragmentView.findViewById(R.id.earningPerShare);
        yield = fragmentView.findViewById(R.id.yield);
        upEarningDate = fragmentView.findViewById(R.id.earningDate);
        upEarningEstimate = fragmentView.findViewById(R.id.estimate);
        upEarningEstimateRange = fragmentView.findViewById(R.id.estimateRange);
        laEarningActual = fragmentView.findViewById(R.id.earningActual);
        laEarningEstimate = fragmentView.findViewById(R.id.earningLatestEstimate);
        laEarning5D = fragmentView.findViewById(R.id.d5change);
        sector = fragmentView.findViewById(R.id.sector);
        industry = fragmentView.findViewById(R.id.industry);
        style = fragmentView.findViewById(R.id.style);
        employees = fragmentView.findViewById(R.id.employee);
        ceo = fragmentView.findViewById(R.id.ceo);
    }

    public void display() {
        String plusOrMinor = stock.getQuote().getChange() > 0 ? "+" : (stock.getQuote().getChange() < 0 ? "-" : "");

        companyName.setText(stock.getQuote().getCompanyName());
        price.setText(String.format("%.2f", stock.getQuote().getLatestPrice()));
        change.setText(String.format("%s %.2f", plusOrMinor, stock.getQuote().getChange()));
//        changePercent.setText(String.format("%s %s%", plusOrMinor, String.format("%.2f", stock.getQuote().getChangePercent() * 100)));
        upOrDownArrow.setText(plusOrMinor.equals("+") ? "▲" : (plusOrMinor.equals("") ? "" : "▼"));


//        Date date = new Date((long) stock.getQuote().getLatestUpdate());
        TimeZone timeZone = TimeZone.getTimeZone("US/Central");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm MMM dd zzz");
        format.setTimeZone(timeZone);
        String dateTime = format.format(new Date((long) stock.getQuote().getLatestUpdate()));


        time.setText(dateTime);


        open.setText(String.format("%.2f", stock.getQuote().getOpen()));
        previousClose.setText(String.format("%.2f", stock.getQuote().getPreviousClose()));
        dayRange.setText(String.format("%.2f - %.2f", stock.getQuote().getHigh(), stock.getQuote().getLow()));
        volume.setText(formatValue(stock.getQuote().getVolume()));
        averageVolume.setText(formatValue(stock.getQuote().getAvgTotalVolume()));
        last5dayChange.setText("–");
        last3MonthChange.setText("–");
        last1YearChange.setText(String.format("%s %.2f%%", plusOrMinor, stock.getQuote().getYtdChange()));
        week52Range.setText(String.format("%.2f - %.2f", stock.getQuote().getWeek52Low(), stock.getQuote().getWeek52High()));
        marketCap.setText(formatValue(stock.getQuote().getMarketCap()));
        peRatio.setText(String.format("%.2f", stock.getQuote().getPeRatio()));
        earningPerShare.setText("–");
        yield.setText("–");
        upEarningDate.setText("–");
        upEarningEstimate.setText("–");
        upEarningEstimateRange.setText("–");
        laEarningActual.setText("–");
        laEarningEstimate.setText("–");
        laEarning5D.setText("–");
        sector.setText(stock.getCompany().getSector());
        industry.setText(stock.getCompany().getIndustry());
        style.setText("–");
        employees.setText(String.format("%d", stock.getCompany().getEmployees()));
        ceo.setText(stock.getCompany().getCEO());
    }

    public String formatValue(float value) {
        String arr[] = {"", "K", "M", "B", "T", "P", "E"};
        int index = 0;
        while ((value / 1000) >= 1) {
            value = value / 1000;
            index++;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return String.format("%s%s", decimalFormat.format(value), arr[index]);
    }
}
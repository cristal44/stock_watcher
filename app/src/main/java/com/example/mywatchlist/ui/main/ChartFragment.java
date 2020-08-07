package com.example.mywatchlist.ui.main;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.mywatchlist.R;
import com.example.mywatchlist.View.BaseView;
import com.example.mywatchlist.View.DetailsActivity;
import com.example.mywatchlist.data.ChartPresenter;
import com.example.mywatchlist.data.Data;
import com.example.mywatchlist.data.Stock;
import com.example.mywatchlist.data.StockData;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import java.util.ArrayList;
import java.util.List;
import static android.graphics.Color.GRAY;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.rgb;


public class ChartFragment extends Fragment implements BaseView{

    private Stock stock;

    private int filledUpColor = Color.argb(150,15,157,88);
    private int filledDownColor = Color.argb(150,219,68,55);

    private LineChart lineChart;

    private TextView day1, day5, month1, month6, year1, all;

    private String symbol;

    private ChartPresenter chartPresenter;

    private XAxis xAxis;

    private List<StockData> dataList = new ArrayList<>();
    private String clickTextView = "";

    private TextView selectedTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chartPresenter = new ChartPresenter(this);

        DetailsActivity detailsActivity = (DetailsActivity) getActivity();
        stock = detailsActivity.getData();
        symbol = stock.getQuote().getSymbol();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_chart, container, false);
        day1 = fragmentView.findViewById(R.id.d1);
        day5 = fragmentView.findViewById(R.id.d5);
        month1 = fragmentView.findViewById(R.id.m1);
        month6 = fragmentView.findViewById(R.id.m6);
        year1 = fragmentView.findViewById(R.id.y1);
        all = fragmentView.findViewById(R.id.all);

        lineChart = (LineChart) fragmentView.findViewById(R.id.lineChart);
        xAxis = lineChart.getXAxis();
        YAxis rightAxis = lineChart.getAxisRight();
        YAxis leftAxis = lineChart.getAxisLeft();


        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawBorders(false);

        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);

        Description description = lineChart.getDescription();
        description.setEnabled(false);


        xAxis.setDrawGridLines(true);
        xAxis.setDrawLabels(true);
        xAxis.setLabelCount(5);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        xAxis.setAvoidFirstLastClipping(true);

        leftAxis.setEnabled(false);
        rightAxis.setLabelCount(4);
        rightAxis.setDrawAxisLine(true);

        setData(dataList);


        day1.setBackgroundColor(GRAY);
        day1.setTextColor(WHITE);
        clickTextView = day1.getText().toString();
        chartPresenter.getCharts(symbol, "today");

        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoreBackGroundColor(clickTextView);
                day1.setBackgroundColor(GRAY);
                day1.setTextColor(WHITE);
                clickTextView = day1.getText().toString();
                chartPresenter.getCharts(symbol, "today");
            }
        });

        day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoreBackGroundColor(clickTextView);
                day5.setBackgroundColor(GRAY);
                day5.setTextColor(WHITE);
                clickTextView = day5.getText().toString();
                chartPresenter.getCharts(symbol,"5dm");
            }
        });


        month1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoreBackGroundColor(clickTextView);
                month1.setBackgroundColor(GRAY);
                month1.setTextColor(WHITE);
                clickTextView = month1.getText().toString();
                chartPresenter.getCharts(symbol,"1m");

            }
        });

        month6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoreBackGroundColor(clickTextView);
                month6.setBackgroundColor(GRAY);
                month6.setTextColor(WHITE);
                clickTextView = month6.getText().toString();
                chartPresenter.getCharts(symbol,"6m");

            }
        });

        year1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoreBackGroundColor(clickTextView);
                year1.setBackgroundColor(GRAY);
                year1.setTextColor(WHITE);
                clickTextView = year1.getText().toString();
                chartPresenter.getCharts(symbol,"1y");

            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoreBackGroundColor(clickTextView);
                all.setBackgroundColor(GRAY);
                all.setTextColor(WHITE);
                clickTextView = all.getText().toString();
                chartPresenter.getCharts(symbol,"max");

            }
        });

        return fragmentView;
    }

    public void restoreBackGroundColor(String text){
        String selectedDate = "";
        if (text.equals(day1.getText().toString())){
            day1.setBackgroundColor(WHITE);
            day1.setTextColor(GRAY);
        }

        if (text.equals(day5.getText().toString())){
            day5.setBackgroundColor(WHITE);
            day5.setTextColor(GRAY);
        }

        if (text.equals(month1.getText().toString())){
            month1.setBackgroundColor(WHITE);
            month1.setTextColor(GRAY);
        }

        if (text.equals(month6.getText().toString())){
            month6.setBackgroundColor(WHITE);
            month6.setTextColor(GRAY);
        }

        if (text.equals(year1.getText().toString())){
            year1.setBackgroundColor(WHITE);
            year1.setTextColor(GRAY);
        }

        if (text.equals(all.getText().toString())){
            all.setBackgroundColor(WHITE);
            all.setTextColor(GRAY);
        }

    }


    public String getLabel(Data data){

        if (clickTextView.equals(day1.getText().toString())){
            return data.getMinute();
        }

        if (clickTextView.equals(day5.getText().toString()) || clickTextView.equals(month1.getText().toString())){
            return data.getDate().split("-")[2];
        }

        if (clickTextView.equals(month6.getText().toString()) || clickTextView.equals(year1.getText().toString())){
            return data.getLabel().split(" ")[0];
        }


        if (clickTextView.equals(all.getText().toString())){
            return data.getDate().split("-")[0];
        }

        return null;

    }


    private void setData(List<StockData> dataList) {

        ArrayList<String> xAxisValues = new ArrayList<>();
        ArrayList<Entry> yValues = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            Data data = (Data) dataList.get(i);
            xAxisValues.add(getLabel(data));
            double price = data.getHigh();
            yValues.add(new Entry(i, (float) price));
        }

        LineDataSet set1;

        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0){
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(yValues);
            set1.notifyDataSetChanged();
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        } else {
            set1 = new LineDataSet(yValues, "Data Set 1");
            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(rgb(15, 157, 88));
            set1.setDrawCircles(false);
            set1.setDrawFilled(true);
            set1.setFillColor(stock.getQuote().getChange() >= 0 ? filledUpColor : filledDownColor);

            LineData data = new LineData(set1);
            data.setDrawValues(false);

            lineChart.setData(data);

            xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        }
    }



    @Override
    public void display() {

    }

    @Override
    public void updateData(List<StockData> data) {
        List<StockData> dataList1 = new ArrayList<>();

        for (int i = 0; i < data.size(); i++){
            dataList1.add(data.get(i));
        }

        setData(dataList1);
        lineChart.invalidate();

    }

}

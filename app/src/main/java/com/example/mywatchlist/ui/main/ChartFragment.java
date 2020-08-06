package com.example.mywatchlist.ui.main;

import android.bluetooth.BluetoothA2dp;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mywatchlist.R;
import com.example.mywatchlist.View.BaseView;
import com.example.mywatchlist.View.DetailsActivity;
import com.example.mywatchlist.data.Chart;
import com.example.mywatchlist.data.ChartPresenter;
import com.example.mywatchlist.data.Charts;
import com.example.mywatchlist.data.Data;
import com.example.mywatchlist.data.Stock;
import com.example.mywatchlist.data.StockData;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;
import static android.graphics.Color.CYAN;
import static android.graphics.Color.RED;
import static android.graphics.Color.rgb;

public class ChartFragment extends Fragment implements BaseView {

    private Stock stock;
    private List<Chart> chartList = new ArrayList<>();
    private int filledColor = Color.argb(150,15,157,88);

    private static final String TAG = "ChartFragment";
    private LineChart lineChart;
    private double maxValue;
    private TextView day1;

    private String symbol;

    ChartPresenter chartPresenter;

    XAxis xAxis;

    private List<Data> dataList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        DetailsActivity detailsActivity = (DetailsActivity) getActivity();
        stock = detailsActivity.getData();
        symbol = stock.getQuote().getSymbol();
//        chartList = stock.getChart();


        System.out.println("999999");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_chart, container, false);


        day1 = fragmentView.findViewById(R.id.d1);
        day1.setBackgroundColor(BLUE);
        ChartPresenter chartPresenter = new ChartPresenter(this);

        chartPresenter.getTodayData(symbol);
//        day1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                day1.setBackgroundColor(BLUE);
//
//            }
//        });


        System.out.println(dataList.size());

        lineChart = (LineChart) fragmentView.findViewById(R.id.lineChart);

//        lineChart.setDrawGridBackground(true);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        lineChart.setDrawBorders(false);

        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);

        Description description = lineChart.getDescription();
        description.setEnabled(false);



//        XAxis xAxis = lineChart.getXAxis();
//
//
////        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
////        xAxis.setEnabled(false);
//        xAxis.setDrawGridLines(true);
////        xAxis.setDrawAxisLine(true);
//        xAxis.setDrawLabels(true);
//        xAxis.setLabelCount(5);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawAxisLine(false);
//        xAxis.setAvoidFirstLastClipping(true);
//
//
//
//        YAxis rightAxis = lineChart.getAxisRight();
//        lineChart.getAxisLeft().setEnabled(false);
//        rightAxis.setLabelCount(4);
//        rightAxis.setAxisMaximum((float) maxValue);
//
//        rightAxis.setDrawAxisLine(true);



        return fragmentView;
    }


    private void setData( XAxis xAxis) {

        ArrayList<String> xAxisValues = new ArrayList<>();

        ArrayList<Entry> yValues = new ArrayList<>();

        maxValue = Double.MIN_VALUE;

        for(int i = 0; i < dataList.size(); i++){
            Data data = dataList.get(i);
            xAxisValues.add(data.getMinute());
            double price = data.getHigh();
            if (price > maxValue){
                maxValue = price;
            }
            yValues.add(new Entry(i, (float) price));
        }

        LineDataSet set1 = new LineDataSet(yValues, "Data Set 1");
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(rgb(15,157,88));
        set1.setDrawCircles(false);
        set1.setDrawFilled(true);
        set1.setFillColor(filledColor);

        LineData data = new LineData(set1);
        data.setDrawValues(false);

        lineChart.setData(data);

        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));

    }

//    private void setData( XAxis xAxis) {
//
//        ArrayList<String> xAxisValues = new ArrayList<>();
//
//        ArrayList<Entry> yValues = new ArrayList<>();
//
//        maxValue = Double.MIN_VALUE;
//
//        for(int i = 0; i < chartList.size(); i++){
//            Chart chart = chartList.get(i);
//            xAxisValues.add(chart.getLabel());
//            double price = chart.getHigh();
//            if (price > maxValue){
//                maxValue = price;
//            }
//            yValues.add(new Entry(i, (float) price));
//        }
//
//        LineDataSet set1 = new LineDataSet(yValues, "Data Set 1");
//        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set1.setColor(rgb(15,157,88));
//        set1.setDrawCircles(false);
//        set1.setDrawFilled(true);
//        set1.setFillColor(filledColor);
//
//        LineData data = new LineData(set1);
//        data.setDrawValues(false);
//
//        lineChart.setData(data);
//
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
//
//    }

    public void clickRange(View view){


//        switch (TextViewgetId()){
//            case R.id.d1:
//                R.id.d1.
//        }
    }

    @Override
    public void display() {

    }

    @Override
    public void updateData(List<StockData> list) {

    }

    public void getChart(Charts charts) {

//        chartList.clear();
//        for (int i = 0; i < charts.size(); i++){
//            chartList.add(charts.get(i));
//        }

    }

    public void getTodayData(List<Data> data) {
        dataList.clear();
        System.out.println("11111111111111111111111111111111111got data from chartPresenter");
        for (int i = 0; i < data.size(); i++){
            dataList.add(data.get(i));
            System.out.println("11111111111111111111111111111111111got data from chartPresenter");
        }

        System.out.println(dataList.size());
        XAxis xAxis = lineChart.getXAxis();


//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setEnabled(false);
        xAxis.setDrawGridLines(true);
//        xAxis.setDrawAxisLine(true);
        xAxis.setDrawLabels(true);
        xAxis.setLabelCount(5);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        xAxis.setAvoidFirstLastClipping(true);

        setData(xAxis);


        YAxis rightAxis = lineChart.getAxisRight();
        lineChart.getAxisLeft().setEnabled(false);
        rightAxis.setLabelCount(4);
        rightAxis.setAxisMaximum((float) maxValue);

        rightAxis.setDrawAxisLine(true);

    }
}
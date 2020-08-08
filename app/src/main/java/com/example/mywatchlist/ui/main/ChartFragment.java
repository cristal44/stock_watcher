package com.example.mywatchlist.ui.main;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.mywatchlist.R;
import com.example.mywatchlist.Utils;
import com.example.mywatchlist.View.BaseView;
import com.example.mywatchlist.View.DetailsActivity;
import com.example.mywatchlist.data.ChartPresenter;
import com.example.mywatchlist.data.Data;
import com.example.mywatchlist.data.Stock;
import com.example.mywatchlist.data.StockData;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.BarLineChartTouchListener;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GRAY;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.rgb;


public class ChartFragment extends Fragment implements BaseView,OnChartValueSelectedListener {

    private Stock stock;

    private int filledUpColor = Color.argb(150,15,157,88);
    private int filledDownColor = Color.argb(150,219,68,55);

    private LineChart lineChart;

    private TextView day1, day5, month1, month6, year1, all, chartOpen, chartClose, chartHigh, chartLow, chartVolume, chartAverageVolume ,chartTime;

    private String symbol;

    private ChartPresenter chartPresenter;

    private XAxis xAxis;

    private List<StockData> dataList = new ArrayList<>();
    private String clickTextView = "";


    private Map<Entry, Data> map = new HashMap<>();


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
        inti(fragmentView);
        intiChart();
        setClickableTextView();

        return fragmentView;
    }

    private void setClickableTextView() {
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
    }

    private void intiChart() {
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawBorders(false);
        lineChart.setOnChartValueSelectedListener(this);

        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);

        Description description = lineChart.getDescription();
        description.setEnabled(false);

        xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(true);
        xAxis.setDrawLabels(true);
        xAxis.setLabelCount(5);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        xAxis.setAvoidFirstLastClipping(true);

        YAxis rightAxis = lineChart.getAxisRight();
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setEnabled(false);
        rightAxis.setLabelCount(4);
        rightAxis.setDrawAxisLine(true);

        setData(dataList);
    }

    private void inti(View fragmentView) {
        day1 = fragmentView.findViewById(R.id.d1);
        day5 = fragmentView.findViewById(R.id.d5);
        month1 = fragmentView.findViewById(R.id.m1);
        month6 = fragmentView.findViewById(R.id.m6);
        year1 = fragmentView.findViewById(R.id.y1);
        all = fragmentView.findViewById(R.id.all);
        chartOpen = fragmentView.findViewById(R.id.chartOpen);
        chartClose = fragmentView.findViewById(R.id.chartClose);
        chartHigh = fragmentView.findViewById(R.id.chartHigh);
        chartLow = fragmentView.findViewById(R.id.chartLow);
        chartTime = fragmentView.findViewById(R.id.chartTime);
        chartVolume = fragmentView.findViewById(R.id.chartVolume);
        chartAverageVolume = fragmentView.findViewById(R.id.chartAverageVolume);

        lineChart = (LineChart) fragmentView.findViewById(R.id.lineChart);

        chartOpen.setText(String.format("%.2f", stock.getQuote().getOpen()));
        chartClose.setText(String.format("%.2f", stock.getQuote().getClose()));
        chartHigh.setText(String.format("%.2f", stock.getQuote().getHigh()));
        chartLow.setText(String.format("%.2f", stock.getQuote().getLow()));
        chartTime.setText(stock.getQuote().getLatestTime());
        chartVolume.setText("-");
        chartAverageVolume.setText("-");

        day1.setBackgroundColor(GRAY);
        day1.setTextColor(WHITE);
        clickTextView = day1.getText().toString();
        chartPresenter.getCharts(symbol, "today");
    }


    public void restoreBackGroundColor(String text){
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
            return data.getDate().split(" ")[1].split(",")[0];
        }

        if (clickTextView.equals(month6.getText().toString()) || clickTextView.equals(year1.getText().toString())){
            return data.getLabel().split(" ")[0];
        }


        if (clickTextView.equals(all.getText().toString())){
            return data.getDate().split(",")[1];
        }

        return null;

    }


    private void setData(List<StockData> dataList) {

        ArrayList<String> xAxisValues = new ArrayList<>();
        ArrayList<Entry> yValues = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {

            Data data = (Data) dataList.get(i);

            if (data != null) {
                String xValue = getLabel(data);
                xAxisValues.add(xValue);
                float price = data.getHigh();
                Entry entry = new Entry(i, price);
                yValues.add(entry);

                map.put(entry, data);
            }

        }

        LineDataSet set1;

        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0){
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(yValues);
            set1.notifyDataSetChanged();
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(yValues, "Data Set 1");
            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(stock.getQuote().getChange() >= 0 ? Utils.GREEN : Utils.RED);
            set1.setDrawCircles(false);
            set1.setDrawFilled(true);
            set1.setFillColor(stock.getQuote().getChange() >= 0 ? filledUpColor : filledDownColor);

            LineData data = new LineData(set1);
            data.setDrawValues(false);

            lineChart.setData(data);

        }

        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        set1.setHighlightEnabled(true);
        set1.setHighlightLineWidth(1.2f);
        set1.setHighLightColor(BLACK);
        set1.setDrawVerticalHighlightIndicator(true);
        set1.setDrawHorizontalHighlightIndicator(false);

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


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Data data = map.get(e);

        chartOpen.setText(data.getOpen());
        chartClose.setText(data.getClose());
        chartHigh.setText(data.getHighString());
        chartLow.setText(data.getLow());
        chartVolume.setText(data.getVolume());
        chartAverageVolume.setText(data.getAverage());
        chartTime.setText(String.format("%s%s",data.getDate() , data.getMinute() == null ? "" :   " at " + data.getLabel()));
    }

    @Override
    public void onNothingSelected() {

    }
}

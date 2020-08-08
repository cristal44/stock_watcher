package com.example.mywatchlist.ui.main;

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
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static android.graphics.Color.BLACK;
import static android.graphics.Color.GRAY;
import static android.graphics.Color.WHITE;


public class ChartFragment extends Fragment implements BaseView,OnChartValueSelectedListener, View.OnTouchListener {

    private Stock stock;

    private int filledUpColor = Color.argb(150,15,157,88);
    private int filledDownColor = Color.argb(150,219,68,55);
    private int filledSelectedColor =Color.argb(50, 34, 87, 245);
    private int selectedColor = Color.rgb(34, 87, 245);

    private LineChart lineChart;
    private XAxis xAxis;
    private TextView day1, day5, month1, month6, year1, all, chartOpen, chartClose, chartHigh, chartLow, chartVolume, chartAverageVolume ,chartTime;
    private String symbol;
    private String clickTextView = "";
    private ChartPresenter chartPresenter;
    private List<StockData> dataList = new ArrayList<>();
    private Map<Entry, Data> map = new HashMap<>();

    private boolean isPress = false;
    private boolean isChange = true;

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
                restoreTextViewBackGroundColor(clickTextView);
                day1.setBackgroundColor(GRAY);
                day1.setTextColor(WHITE);
                isChange = true;
                clickTextView = day1.getText().toString();
                chartPresenter.getCharts(symbol, "today");
            }
        });

        day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoreTextViewBackGroundColor(clickTextView);
                day5.setBackgroundColor(GRAY);
                day5.setTextColor(WHITE);
                isChange = false;
                clickTextView = day5.getText().toString();
                chartPresenter.getCharts(symbol,"5dm");
            }
        });


        month1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoreTextViewBackGroundColor(clickTextView);
                month1.setBackgroundColor(GRAY);
                month1.setTextColor(WHITE);
                isChange = false;
                clickTextView = month1.getText().toString();
                chartPresenter.getCharts(symbol,"1m");

            }
        });

        month6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoreTextViewBackGroundColor(clickTextView);
                month6.setBackgroundColor(GRAY);
                month6.setTextColor(WHITE);
                isChange = false;
                clickTextView = month6.getText().toString();
                chartPresenter.getCharts(symbol,"6m");

            }
        });

        year1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoreTextViewBackGroundColor(clickTextView);
                year1.setBackgroundColor(GRAY);
                year1.setTextColor(WHITE);
                isChange = false;
                clickTextView = year1.getText().toString();
                chartPresenter.getCharts(symbol,"1y");

            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoreTextViewBackGroundColor(clickTextView);
                all.setBackgroundColor(GRAY);
                all.setTextColor(WHITE);
                isChange = false;
                clickTextView = all.getText().toString();
                chartPresenter.getCharts(symbol,"max");

            }
        });
    }

    private void intiChart() {
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);
        lineChart.setDrawBorders(false);
        lineChart.setDoubleTapToZoomEnabled(false);

        lineChart.setOnChartValueSelectedListener(this);
        lineChart.setOnTouchListener(this);

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

        setData();
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

        lineChart = fragmentView.findViewById(R.id.lineChart);

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


    public void restoreTextViewBackGroundColor(String text){
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


    public String getXValue(Data data){

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

    private void setData() {

        ArrayList<String> xAxisValues = new ArrayList<>();
        ArrayList<Entry> yValues = new ArrayList<>();

        System.out.println("SetData");

        for (int i = 0; i < dataList.size(); i++) {
            Data data = (Data) dataList.get(i);
            if (data != null) {
                String xValue = getXValue(data);
                xAxisValues.add(xValue);
                Entry entry = new Entry(i, data.getHigh());
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

            set1.setDrawCircles(false);
            set1.setDrawFilled(true);

            LineData data = new LineData(set1);
            data.setDrawValues(false);

            lineChart.setData(data);

        }

        set1.setColor(isPress ? selectedColor : isChange ? Utils.getColor(stock.getQuote().getChange()) : Utils.GREEN);
        set1.setFillColor(isPress ? filledSelectedColor : isChange? stock.getQuote().getChange() >= 0 ? filledUpColor : filledDownColor : Utils.GREEN);

        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        set1.setHighlightEnabled(true);
        set1.setHighlightLineWidth(1.2f);
        set1.setHighLightColor(isPress ? Color.rgb(3, 46, 173) : BLACK);
        set1.setDrawVerticalHighlightIndicator(isPress ? true : false);
        set1.setDrawHorizontalHighlightIndicator(false);

    }


    @Override
    public void display() {

    }

    @Override
    public void updateData(List<StockData> data) {
        dataList.clear();
        for (int i = 0; i < data.size(); i++){
            dataList.add(data.get(i));
        }

        setData();
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
    public void onNothingSelected() { }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        isPress = true;
        if (isPress && event.getAction() == MotionEvent.ACTION_DOWN) {
            Highlight h = lineChart.getHighlightByTouchPoint(event.getX(),event.getY());
            h.setDraw(event.getX(),event.getY());
            lineChart.highlightValue(h,true);
            setData();
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL){
            isPress = false;
            setData();
        }

        return false;
    }
}

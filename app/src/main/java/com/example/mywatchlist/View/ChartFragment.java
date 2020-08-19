package com.example.mywatchlist.View;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.mywatchlist.MyColor;
import com.example.mywatchlist.R;
import com.example.mywatchlist.presenter.ChartPresenter;
import com.example.mywatchlist.entity.Data;
import com.example.mywatchlist.entity.Stock;
import com.example.mywatchlist.entity.StockData;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import static android.graphics.Color.BLACK;
import static android.graphics.Color.GRAY;
import static android.graphics.Color.WHITE;

public class ChartFragment extends Fragment implements BaseView, OnChartValueSelectedListener, View.OnTouchListener {
    @BindView(R.id.d1) TextView day1;
    @BindView(R.id.d5) TextView day5;
    @BindView(R.id.m1) TextView month1;
    @BindView(R.id.m6) TextView month6;
    @BindView(R.id.y1) TextView year1;
    @BindView(R.id.all) TextView all;
    @BindView(R.id.chartOpen) TextView chartOpen;
    @BindView(R.id.chartClose) TextView chartClose;
    @BindView(R.id.chartHigh) TextView chartHigh;
    @BindView(R.id.chartLow) TextView chartLow;
    @BindView(R.id.chartTime) TextView chartTime;
    @BindView(R.id.chartVolume) TextView chartVolume;
    @BindView(R.id.chartAverageVolume) TextView chartAverageVolume;
    @BindView(R.id.lineChart) LineChart lineChart;
    @BindView(R.id.barChart) BarChart barChart;

    private Stock stock;
    private XAxis xAxis;
    private String symbol;
    private ChartPresenter chartPresenter;
    private List<StockData> dataList = new ArrayList<>();
    private Map<Entry, Data> map = new HashMap<>();
    private boolean isPress = false;
    private int filledColor;
    private int lineColor;
    private LineDataSet set1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chartPresenter = new ChartPresenter(this);
        DetailsActivity detailsActivity = (DetailsActivity) getActivity();
        stock = detailsActivity.getData();
        symbol = stock.getSymbol();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_chart, container, false);
        ButterKnife.bind(this, fragmentView);
        inti();

        intiLineChart();
        setClickableTextView();

        intiBarChart();

        return fragmentView;
    }

    private void inti() {
        chartOpen.setText(String.format("%.2f", stock.getQuote().getOpen()));
        chartClose.setText(String.format("%.2f", stock.getQuote().getClose()));
        chartHigh.setText(String.format("%.2f", stock.getQuote().getHigh()));
        chartLow.setText(String.format("%.2f", stock.getQuote().getLow()));
        chartTime.setText(stock.getQuote().getLatestTime());
        chartVolume.setText(stock.getQuote().getVolume());
        chartAverageVolume.setText(stock.getQuote().getAvgTotalVolume());

        day1.setBackgroundColor(GRAY);
        day1.setTextColor(WHITE);
        lineColor = stock.getQuote().getColor();
        filledColor = stock.getQuote().getColor();

        getData("today");
    }

    private void intiBarChart(){
        barChart.getLegend().setEnabled(false);
        barChart.getDescription().setEnabled(false);

        barChart.getXAxis().setEnabled(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);

        setDataForBarChart();
    }

    private void intiLineChart() {
        lineChart.getLegend().setEnabled(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.getAxisLeft().setEnabled(false);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);
        lineChart.setDrawBorders(false);
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setOnChartValueSelectedListener(this);
        lineChart.setOnTouchListener(this);

        xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setLabelCount(4);
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        rightAxis.setDrawGridLines(true);

        setDataForLineChart();
    }

    private void getData(String range) {
        chartPresenter.getData(symbol + "," + range);
    }

    private void setClickableTextView() {
        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllClickableTextViewColor();
                setClickColor(day1);
                getData("today");
            }
        });

        day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllClickableTextViewColor();
                setClickColor(day5);
                getData("5dm");
            }
        });


        month1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllClickableTextViewColor();
                setClickColor(month1);
                getData("1mm");

            }
        });

        month6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllClickableTextViewColor();
                setClickColor(month6);
                getData("6m");

            }
        });

        year1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllClickableTextViewColor();
                setClickColor(year1);
                getData("1y");

            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllClickableTextViewColor();
                setClickColor(all);
                getData("max");
            }
        });
    }

    public void setDataForBarChart(){
        List<BarEntry> values = new ArrayList<>();

        if (!dataList.isEmpty()){
            for (int i = 0; i < dataList.size(); i++){
                Data data = (Data) dataList.get(i);
                values.add(new BarEntry(i, data.getVolume()));
            }

        }

        BarDataSet set1;
        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0){
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues((values));
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "volume");
            set1.setDrawIcons(false);

            set1.setColor(GRAY);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setDrawValues(false);
            barChart.setData(data);
        }
    }


    private void setDataForLineChart() {
        ArrayList<String> xAxisValues = new ArrayList<>();
        ArrayList<Entry> yValues = new ArrayList<>();

        if (!dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                Data data = (Data) dataList.get(i);
                xAxisValues.add(data.getxValueString());
                Entry entry = new Entry(i, data.getHigh());
                yValues.add(entry);
                map.put(entry, data);
            }
        }

        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(yValues);
            set1.notifyDataSetChanged();
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(yValues, "Data Set 1");
            LineData data = new LineData(set1);
            data.setDrawValues(false);
            lineChart.setData(data);
        }

        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));

        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setDrawCircles(false);
        set1.setDrawFilled(true);
        set1.setColor(isPress ? MyColor.BLUE : lineColor);
        set1.setFillColor(isPress ? MyColor.ALPHBLUE : filledColor);
        set1.setHighlightEnabled(true);
        set1.setHighlightLineWidth(1.2f);
        set1.setHighLightColor(isPress ? Color.rgb(3, 46, 173) : BLACK);
        set1.setDrawVerticalHighlightIndicator(isPress ? true : false);
        set1.setDrawHorizontalHighlightIndicator(false);
    }

    @Override
    public void display(List<StockData> data) {
        dataList.clear();
        for (int i = 0; i < data.size(); i++) {
            dataList.add(data.get(i));
        }
        setDataForLineChart();
        setDataForBarChart();

        barChart.invalidate();

        lineChart.invalidate();
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Data data = map.get(e);
        chartOpen.setText(data.getOpen());
        chartClose.setText(data.getClose());
        chartHigh.setText(data.getHighString());
        chartLow.setText(data.getLow());
        chartVolume.setText(data.getVolumeString());
        chartAverageVolume.setText(data.getAverage());
        chartTime.setText(String.format("%s%s", data.getDate(), data.getMinute() == null ? "" : " at " + data.getLabel()));
    }

    @Override
    public void onNothingSelected() { }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        isPress = true;
        if (isPress && event.getAction() == MotionEvent.ACTION_DOWN) {
            Highlight h = lineChart.getHighlightByTouchPoint(event.getX(), event.getY());
            h.setDraw(event.getX(), event.getY());
            lineChart.highlightValue(h, true);
            setDataForLineChart();
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            isPress = false;
            setDataForLineChart();
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (chartPresenter != null) {
            chartPresenter = null;
        }
    }

    private void setAllClickableTextViewColor() {
        setOriginalColor(day1);
        setOriginalColor(day5);
        setOriginalColor(month1);
        setOriginalColor(month6);
        setOriginalColor(year1);
        setOriginalColor(all);
    }

    private void setOriginalColor(TextView textView) {
        textView.setBackgroundColor(WHITE);
        textView.setTextColor(GRAY);
    }

    private void setClickColor(TextView textView) {
        textView.setBackgroundColor(GRAY);
        textView.setTextColor(WHITE);
        lineColor = textView.getId() == R.id.d1 ? stock.getQuote().getColor() : MyColor.GREEN;
        filledColor = textView.getId() == R.id.d1 ? stock.getQuote().getColor() : MyColor.GREEN;
    }
}
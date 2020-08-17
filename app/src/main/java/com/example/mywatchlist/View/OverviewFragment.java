package com.example.mywatchlist.View;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.mywatchlist.R;
import com.example.mywatchlist.Utils;
import com.example.mywatchlist.entity.Earning;
import com.example.mywatchlist.entity.Estimate;
import com.example.mywatchlist.entity.Stock;
import butterknife.BindView;
import butterknife.ButterKnife;
import static android.graphics.Color.WHITE;

public class OverviewFragment extends Fragment {
    @BindView(R.id.overviewCompanyName) TextView companyName;
    @BindView(R.id.overviewPrice) TextView price;
    @BindView(R.id.overviewChange) TextView change;
    @BindView(R.id.overviewPercentage) TextView changePercent;
    @BindView(R.id.overviewArrow) TextView upOrDownArrow;
    @BindView(R.id.overviewDate) TextView time;
    @BindView(R.id.open) TextView open;
    @BindView(R.id.previousClose) TextView previousClose;
    @BindView(R.id.dayRange) TextView dayRange;
    @BindView(R.id.volume) TextView volume;
    @BindView(R.id.averageVolume) TextView averageVolume;
    @BindView(R.id.day5Change) TextView last5dayChange;
    @BindView(R.id.mo3Change) TextView last3MonthChange;
    @BindView(R.id.yearChange) TextView last1YearChange;
    @BindView(R.id.week52Range) TextView week52Range;
    @BindView(R.id.marketCap) TextView marketCap;
    @BindView(R.id.peRatio) TextView peRatio;
    @BindView(R.id.sharesOutstanding) TextView sharesOutstanding;
    @BindView(R.id.beta) TextView beta;
    @BindView(R.id.reportDate) TextView reportDate;
    @BindView(R.id.estimate) TextView upEarningEstimate;
    @BindView(R.id.estimateRange) TextView upEarningEstimateRange;
    @BindView(R.id.earningActual) TextView laEarningActual;
    @BindView(R.id.earningLatestEstimate) TextView laEarningEstimate;
    @BindView(R.id.d5change) TextView laEarning5D;
    @BindView(R.id.sector) TextView sector;
    @BindView(R.id.industry) TextView industry;
    @BindView(R.id.website) TextView website;
    @BindView(R.id.employee) TextView employees;
    @BindView(R.id.ceo) TextView ceo;
    @BindView(R.id.overviewLayout) RelativeLayout overviewLayout;
    @BindView(R.id.keyLayout) LinearLayout keyLayout;
    @BindView(R.id.earningGridLayout) GridLayout earningLayout;
    @BindView(R.id.companyProfileLayout) LinearLayout companyProfileLayout;
    @BindView(R.id.openOrCloseTextview) TextView estimateAnnouncedTime;

    private Stock stock;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DetailsActivity detailsActivity = (DetailsActivity) getActivity();
        stock = detailsActivity.getData();
        display();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_overview, container, false);
        ButterKnife.bind(this,fragmentView);
        setLayoutBackground();

        return fragmentView;
    }

    public void setLayoutBackground(){
        GradientDrawable backgroundGradient1 = (GradientDrawable) overviewLayout.getBackground();
        backgroundGradient1.setColor(WHITE);

        GradientDrawable backgroundGradient2 = (GradientDrawable) keyLayout.getBackground();
        backgroundGradient2.setColor(WHITE);

        GradientDrawable backgroundGradient3 = (GradientDrawable) earningLayout.getBackground();
        backgroundGradient3.setColor(WHITE);

        GradientDrawable backgroundGradient4 = (GradientDrawable) companyProfileLayout.getBackground();
        backgroundGradient4.setColor(WHITE);
    }

    public void display() {
        companyName.setText(stock.getQuote().getCompanyName());
        price.setText(String.format("%.2f", stock.getQuote().getLatestPrice()));
        change.setText(String.format("%s %.2f", stock.getQuote().getPlusOrMinors(), Math.abs(stock.getQuote().getChange())));
        change.setTextColor(stock.getQuote().getColor());
        changePercent.setText(String.format("(%s %.2f%%)",stock.getQuote().getPlusOrMinors(),Math.abs(stock.getQuote().getChangePercent() * 100)));
        changePercent.setTextColor(stock.getQuote().getColor());
        upOrDownArrow.setText(stock.getQuote().getUpOrDownArrow());
        upOrDownArrow.setTextColor(stock.getQuote().getColor());
        time.setText(Utils.getFormatTime(stock.getQuote().getLatestUpdate()));
        open.setText(String.format("%.2f", stock.getQuote().getOpen()));
        previousClose.setText(String.format("%.2f", stock.getQuote().getPreviousClose()));
        dayRange.setText(String.format("%.2f - %.2f", stock.getQuote().getHigh(), stock.getQuote().getLow()));
        volume.setText(stock.getQuote().getVolume());
        averageVolume.setText(stock.getQuote().getAvgTotalVolume());

        double d5Change = stock.getStats().getDay5ChangePercent();
        last5dayChange.setText(String.format("%s %.2f%%", Utils.getPlusOrMinors(d5Change), Math.abs(d5Change)));
        last5dayChange.setTextColor(Utils.getColor(d5Change));

        double m6Change = stock.getStats().getMonth6ChangePercent();
        last3MonthChange.setText(String.format("%s %.2f%%", Utils.getPlusOrMinors(m6Change), Math.abs(m6Change)));
        last3MonthChange.setTextColor(Utils.getColor(m6Change));

        double yearChange = stock.getStats().getYear1ChangePercent();
        last1YearChange.setText(String.format("%s %.2f%%", Utils.getPlusOrMinors(yearChange), Math.abs(yearChange)));
        last1YearChange.setTextColor(Utils.getColor(yearChange));

        week52Range.setText(String.format("%.2f - %.2f", stock.getQuote().getWeek52Low(), stock.getQuote().getWeek52High()));
        marketCap.setText(stock.getQuote().getMarketCap());
        peRatio.setText(String.format("%.2f", stock.getQuote().getPeRatio()));
        sharesOutstanding.setText(stock.getStats().getSharesOutstanding());

        beta.setText(String.format("%.2f",stock.getStats().getBeta()));

        Estimate estimate = stock.getEstimates().getEstimates().get(0);
        reportDate.setText(estimate == null ? "-" : estimate.getReportDate());
        upEarningEstimate.setText(String.format("%.2f", estimate.getConsensusEPS()));
        upEarningEstimateRange.setText(String.format("%d",estimate.getNumberOfEstimates()));
//        estimateAnnouncedTime.setText(estimate.getAnnounceTime());

        Earning earning = stock.getEarnings().getEarnings().get(0);
        laEarningActual.setText(String.format("%.2f",earning.getActualEPS()));
        laEarningEstimate.setText(String.format("%.2f", earning.getConsensusEPS()));
        laEarning5D.setText(String.format("%d", earning.getNumberOfEstimates()));
        sector.setText(stock.getCompany().getSector());
        industry.setText(stock.getCompany().getIndustry());

        website.setText(stock.getCompany().getWebsite());
        Linkify.addLinks(website, Linkify.WEB_URLS);

        employees.setText(String.format("%d", stock.getCompany().getEmployees()));
        ceo.setText(stock.getCompany().getCEO());
    }
}
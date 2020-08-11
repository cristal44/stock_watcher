package com.example.mywatchlist.ui.adapter;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mywatchlist.R;
import com.example.mywatchlist.View.MainActivity;
import com.example.mywatchlist.entity.Stock;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.MyViewHolder> {
    private List<Stock> stockList;
    private MainActivity mainActivity;

    public WatchlistAdapter(List<Stock> stockList, MainActivity mainActivity) {
        this.stockList = stockList;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public WatchlistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stocklist, parent, false);
        itemView.setOnClickListener(mainActivity);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchlistAdapter.MyViewHolder holder, int position) {
        Stock stock = stockList.get(position);

        holder.stockSymbol.setText(stock.getQuote().getSymbol());
        holder.stockCompanyName.setText(stock.getQuote().getCompanyName());
        holder.stockPrice.setText(String.format("%.2f", stock.getQuote().getLatestPrice()));
        holder.stockChange.setText(String.format("%.2f", Math.abs(stock.getQuote().getChange())));
        holder.stockPercentage.setText(String.format("%.2f%%", Math.abs(stock.getQuote().getChangePercent() * 100)));
        holder.stockPlusOrMinor.setText(stock.getQuote().getPlusOrMinors());

        holder.viewColor.setBackgroundResource(R.drawable.custom_rounded_corners);
        GradientDrawable drawable = (GradientDrawable) holder.viewColor.getBackground();
        drawable.setColor(stock.getQuote().getColor());
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.symbol) TextView stockSymbol;
        @BindView(R.id.price) TextView stockPrice;
        @BindView(R.id.change) TextView stockChange;
        @BindView(R.id.percentage) TextView stockPercentage;
        @BindView(R.id.name) TextView stockCompanyName;
        @BindView(R.id.plusOrMinus) TextView stockPlusOrMinor;
        @BindView(R.id.view) View viewColor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
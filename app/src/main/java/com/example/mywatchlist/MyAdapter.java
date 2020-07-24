package com.example.mywatchlist;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Stock> stockList;
    private MainActivity mainActivity;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView stockSymbol;
        TextView stockPrice;
        TextView stockChange;
        TextView stockPercentage;
        TextView stockCompanyName;
        TextView stockPlusOrMinor;
        View viewColor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stockSymbol = itemView.findViewById(R.id.symbol);
            stockPrice = itemView.findViewById(R.id.price);
            stockChange = itemView.findViewById(R.id.change);
            stockPercentage = itemView.findViewById(R.id.percentage);
            stockCompanyName = itemView.findViewById(R.id.name);
            stockPlusOrMinor = itemView.findViewById(R.id.plusOrMinus);
            viewColor = itemView.findViewById(R.id.view);
        }
    }

    public MyAdapter(List<Stock> stockList, MainActivity mainActivity) {
        this.stockList = stockList;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stocklist, parent, false);
        itemView.setOnClickListener(mainActivity);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Stock stock = stockList.get(position);

        holder.stockSymbol.setText(stock.getSymbol());
        holder.stockCompanyName.setText(stock.getCompanyName());
        holder.stockPrice.setText(stock.getPrice());
        holder.stockChange.setText(stock.getChange());
        holder.stockPercentage.setText(stock.getPercentage());
        holder.stockPlusOrMinor.setText(stock.getPlusOrMinor());

        holder.viewColor.setBackgroundResource(R.drawable.custom_rounded_corners);
        GradientDrawable drawable = (GradientDrawable) holder.viewColor.getBackground();
        drawable.setColor(stock.getColor());

    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }
}

package com.example.mywatchlist.ui.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mywatchlist.R;
import com.example.mywatchlist.View.MainActivity;
import com.example.mywatchlist.entity.Stock;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.MyViewHolder> implements ItemTouchHelperAdapter {
    private List<Stock> stockList;
    private OnStockListener stockListener;
    private ItemTouchHelper itemTouchHelper;
    private MainActivity mainActivity;
    private Stock recentlyDeletedStock;
    private int recentlyDeletedStockPosition;


    public WatchlistAdapter(List<Stock> stockList, OnStockListener stockListener, MainActivity mainActivity) {
        this.stockList = stockList;
        this.stockListener = stockListener;
        this.mainActivity = mainActivity;
    }

    public Context getContext() {
        return mainActivity;
    }


    @NonNull
    @Override
    public WatchlistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stocklist, parent, false);
        return new MyViewHolder(itemView, stockListener);
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

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Stock fromStock = stockList.get(fromPosition);
        stockList.remove(fromStock);
        stockList.add(toPosition, fromStock);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemSwiped(int position) {
        recentlyDeletedStock = stockList.get(position);
        recentlyDeletedStockPosition = position;

        stockList.remove(position);
        mainActivity.stockListChanged(recentlyDeletedStock);

        notifyItemRemoved(position);
        showUndoSnackbar();
    }

    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }


    public void showUndoSnackbar() {
        View view = mainActivity.findViewById(R.id.layout);
        Snackbar snackbar = Snackbar.make(view, "Item was removed from the list", Snackbar.LENGTH_LONG);
        snackbar.setAction("Undo", v -> undoDelete());
        snackbar.show();
    }

    private void undoDelete() {
        stockList.add(recentlyDeletedStockPosition, recentlyDeletedStock);
        mainActivity.stockListChanged(recentlyDeletedStock);
        notifyItemInserted(recentlyDeletedStockPosition);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener,
            View.OnTouchListener,
            GestureDetector.OnGestureListener {

        @BindView(R.id.symbol)
        TextView stockSymbol;
        @BindView(R.id.price)
        TextView stockPrice;
        @BindView(R.id.change)
        TextView stockChange;
        @BindView(R.id.percentage)
        TextView stockPercentage;
        @BindView(R.id.name)
        TextView stockCompanyName;
        @BindView(R.id.plusOrMinus)
        TextView stockPlusOrMinor;
        @BindView(R.id.view)
        View viewColor;

        OnStockListener onStockListener;
        GestureDetector gestureDetector;

        public MyViewHolder(@NonNull View itemView, OnStockListener onStockListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            gestureDetector = new GestureDetector(itemView.getContext(), this);
            this.onStockListener = onStockListener;
            itemView.setOnTouchListener(this);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onStockListener.onStockClick(getAdapterPosition());
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            onStockListener.onStockClick(getAdapterPosition());
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            itemTouchHelper.startDrag(this);

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            gestureDetector.onTouchEvent(event);
            return true;
        }
    }

    public interface OnStockListener {
        void onStockClick(int position);
    }
}
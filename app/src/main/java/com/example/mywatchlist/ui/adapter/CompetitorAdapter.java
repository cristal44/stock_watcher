//package com.example.mywatchlist;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.mywatchlist.data.Stock;
//
//import java.util.List;
//
//public class CompetitorAdapter extends RecyclerView.Adapter<CompetitorAdapter.MyViewHolder> {
//    List<Stock> competitors;
//
//
//    public CompetitorAdapter(List<Stock> competitors) {
//        this.competitors = competitors;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.competitors_layout, parent, false);
//        return new CompetitorAdapter.MyViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Stock stock = competitors.get(position);
//
//        holder.competitorSymbol.setText(stock.getSymbol());
//        holder.competitorCompanyName.setText(stock.getCompanyName());
//        holder.competitorPE.setText(stock.getPe());
//        holder.competitorChange.setText(stock.getChange());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return competitors.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//        TextView competitorSymbol;
//        TextView competitorCompanyName;
//        TextView competitorPE;
//        TextView competitorChange;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            competitorSymbol = itemView.findViewById(R.id.competitorSymbol);
//            competitorCompanyName = itemView.findViewById(R.id.competitorName);
//            competitorPE = itemView.findViewById(R.id.competitorPE);
//            competitorChange = itemView.findViewById(R.id.competitorChange);
//        }
//    }
//
//}

package com.example.mywatchlist.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mywatchlist.R;
import com.example.mywatchlist.Utils;
import com.example.mywatchlist.entity.News;
import com.example.mywatchlist.View.NewsFragment;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyNewsViewHolder> {
    private List<News> newsList;
    private NewsFragment newsFragment;

    public NewsAdapter(List<News> newsList, NewsFragment newsFragment) {
        this.newsList = newsList;
        this.newsFragment = newsFragment;
    }

    @NonNull
    @Override
    public NewsAdapter.MyNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.newslist, parent, false);
        itemView.setOnClickListener(newsFragment);
        return new MyNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyNewsViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.title.setText(news.getHeadline());
        holder.source.setText(news.getSource() );
        holder.date.setText(Utils.getFormatTime(news.getDatetime()));
        Picasso.get().load(news.getImage()).resize(200, 200).into(holder.newsImage);
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public static class MyNewsViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.newsTitle) TextView title;
        @BindView(R.id.newsSource) TextView source;
        @BindView(R.id.newsDate) TextView date;
        @BindView(R.id.newsImage) ImageView newsImage;


        public MyNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
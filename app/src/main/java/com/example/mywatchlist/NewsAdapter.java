package com.example.mywatchlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mywatchlist.data.News;
import com.example.mywatchlist.ui.main.NewsFragment;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

        TimeZone timeZone = TimeZone.getTimeZone("US/Central");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm MMM dd zzz");
        format.setTimeZone(timeZone);

        holder.date.setText(format.format(new Date((long) news.getDatetime())));
        Picasso.get().load(news.getImage()).into(holder.newsImage);
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public static class MyNewsViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView source;
        TextView date;
        ImageView newsImage;

        public MyNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.newsTitle);
            source = itemView.findViewById(R.id.newsSource);
            date = itemView.findViewById(R.id.newsDate);
            newsImage = itemView.findViewById(R.id.newsImage);
        }
    }
}
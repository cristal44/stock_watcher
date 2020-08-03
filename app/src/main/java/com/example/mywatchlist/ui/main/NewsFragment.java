package com.example.mywatchlist.ui.main;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mywatchlist.DisplayNewsActivity;
import com.example.mywatchlist.NewsAdapter;
import com.example.mywatchlist.R;
import com.example.mywatchlist.View.DetailsActivity;
import com.example.mywatchlist.data.News;
import com.example.mywatchlist.data.Stock;
import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment implements View.OnClickListener{
    private List<News> newsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Stock stock;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DetailsActivity detailsActivity = (DetailsActivity) getActivity();
        stock = detailsActivity.getData();
        newsList = stock.getNews();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = fragmentView.findViewById(R.id.newsRecyclerView);
        NewsAdapter newsAdapter = new NewsAdapter(newsList,this);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));


        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        final int position = recyclerView.getChildLayoutPosition(v);
        final News news = newsList.get(position);

        Intent intent = new Intent(getActivity(), DisplayNewsActivity.class);
        intent.putExtra("URL", news.getUrl());
        intent.putExtra("SYMBOL",stock.getQuote().getSymbol());
        startActivity(intent);
    }
}
package com.example.mywatchlist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

public class DisplayNewsActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private TextView title;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_news);

        Intent intent = getIntent();
        String url = intent.getStringExtra("URL");
        String symbol = intent.getStringExtra("SYMBOL");

        init();
        setToolBar(symbol);
        setWebView(url);
    }

    private void init() {
        toolbar = findViewById(R.id.displayNewstoolbar);
        title = findViewById(R.id.displayNewsTitle);
        webView = findViewById(R.id.displayWebView);
    }


    private void setToolBar(String symbol) {
        title.setText(symbol);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onBackPressed();
    }

    private void setWebView(String url) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

}
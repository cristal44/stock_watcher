package com.example.mywatchlist.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.example.mywatchlist.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayNewsActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.displayNewstoolbar) Toolbar toolbar;
    @BindView(R.id.displayNewsTitle) TextView title;
    @BindView(R.id.displayWebView) WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_news);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String url = intent.getStringExtra("URL");
        String symbol = intent.getStringExtra("SYMBOL");

        setToolBar(symbol);
        setWebView(url);
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
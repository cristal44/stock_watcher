package com.example.mywatchlist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import static android.graphics.Color.rgb;

public class DisplayNewsActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_news);

        Intent intent = getIntent();
        String url = intent.getStringExtra("URL");
        String symbol = intent.getStringExtra("SYMBOL");

        Toolbar toolbar = findViewById(R.id.displayNewstoolbar);
        TextView title = findViewById(R.id.displayNewsTitle);

        title.setText(symbol);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(rgb(15,157,88), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(this);

        WebView webView = findViewById(R.id.displayWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }

    @Override
    public void onClick(View v) {
        super.onBackPressed();
    }
}
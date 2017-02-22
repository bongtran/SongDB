package com.bongtran.ntc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class PDFWebViewActivity extends AppCompatActivity {
    private WebView webView;
    //    @BindView(R.id.txt_songName) TextView tvSongName;
//    @BindView(R.id.btn_back) ImageButton btnBack;
    private TextView tvSongName;
    private ImageButton btnBack;
    private int count = 0;
    private boolean willShowAd = false;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfweb_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        webView = (WebView) findViewById(R.id.webView);
        tvSongName = (TextView) findViewById(R.id.txt_songName);
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        String url = getIntent().getStringExtra("url1");
        String name = getIntent().getStringExtra("name");
        count = getIntent().getIntExtra("count", count);
        tvSongName.setText(name);

        loadPFDFile(url);

        if ((count % 3) == 0) {
            willShowAd = true;
            mInterstitialAd = newInterstitialAd();
            loadInterstitial();
        }
    }

    private void loadPFDFile(String pdf_url) {
//        String pdf_url = "http://www.dinh.dk/pdf/badathaygi.pdf";
//        pdf_url ="http://vnexpress.net";
        String url = "http://docs.google.com/gview?embedded=true&url=" + pdf_url;
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setPluginsEnabled(true);
//        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebViewClient(new Callback());
        webView.loadUrl(url);
//        webView.loadUrl(pdf_url);
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, String url) {
            return (false);
        }
    }

    @Override
    public void finish() {
        if (willShowAd)
            showInterstitial();
        super.finish();
    }

    //    @OnClick(R.id.btn_back)
    void back() {
        finish();
    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdClosed() {

            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
        }
    }

    private void loadInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

}

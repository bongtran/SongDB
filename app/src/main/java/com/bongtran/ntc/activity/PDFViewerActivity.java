package com.bongtran.ntc.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageButton;

import com.bongtran.ntc.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PDFViewerActivity extends Activity {
    private String TAG = "PDFViewer";
    PDFView pdfView;
    private int count = 0;
    private boolean willShowAd = false;
    private InterstitialAd mInterstitialAd;
    private String urlString;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                back();
//            }
//        });
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        pdfView = (PDFView) findViewById(R.id.pdfView);
        urlString = getIntent().getStringExtra("url1");
        String name = getIntent().getStringExtra("name");
        final String fileName = URLUtil.guessFileName(urlString, null, null);
        count = getIntent().getIntExtra("count", count);

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage(getString(R.string.dialog_loading));
        dialog.show();
//        Uri uri =  Uri.parse(url);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
//                    Log.d(TAG, urlString);
                    File file;
//            FileUtils.copyURLToFile(url, f);

                    String tDir = System.getProperty("java.io.tmpdir");
                    String path = tDir + fileName;
                    file = new File(path);
//                    file.deleteOnExit();
                    if(!file.exists())
                        FileUtils.copyURLToFile(url, file);
//                    Log.d(TAG, "Copied file");
                    pdfView.fromFile(file)
//                .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                            .enableSwipe(true) // allows to block changing pages using swipe
                            .swipeHorizontal(false)
                            .enableDoubletap(true)
                            .defaultPage(0)
                            // allows to draw something on the current page, usually visible in the middle of the screen
//                .onDraw(onDrawListener)
                            // allows to draw something on all pages, separately for every page. Called only for visible pages
//                .onDrawAll(onDrawListener)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        if(pdfView.getPageCount() > 1){
                            btnBack.setAlpha(0.5f);
                        }
                        dialog.dismiss();
                    }
                }) // called after document is loaded and starts to be rendered
//                .onPageChange(onPageChangeListener)
//                .onPageScroll(onPageScrollListener)
                            .onError(new OnErrorListener() {
                                @Override
                                public void onError(Throwable t) {
//                                    Log.d(TAG, t.getMessage());
                                    dialog.dismiss();
                                    String tDir = System.getProperty("java.io.tmpdir");
                                    String path = tDir + fileName;
                                    File fileToDelete = new File(path);
                                    fileToDelete.deleteOnExit();
                                }
                            })
//                .onRender(onRenderListener) // called after document is rendered for the first time
                            .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                            .password(null)
                            .scrollHandle(null)
                            .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                            // spacing between pages in dp. To define spacing color, set view background
                            .spacing(1)
                            .load();
//                    Log.d(TAG, "Loaded file");

                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                    Log.d(TAG, e.getMessage());
                    dialog.dismiss();
                } catch (IOException e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }
        };

        thread.start();
        if ((count % 3) == 0) {
            willShowAd = true;
            mInterstitialAd = newInterstitialAd();
            loadInterstitial();
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
    //http://www.dinh.dk/pdf/badathaygi.pdf
}

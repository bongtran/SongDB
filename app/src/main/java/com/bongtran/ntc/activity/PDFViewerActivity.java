package com.bongtran.ntc.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bongtran.ntc.R;
import com.joanzapata.pdfview.PDFView;


public class PDFViewerActivity extends AppCompatActivity {

    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        pdfView.fromAsset("http://www.dinh.dk/pdf/badathaygi.pdf")
//                .pages(0, 2, 1, 3, 3, 3)
//                .defaultPage(1)
//                .showMinimap(false)
//                .enableSwipe(true)
////                .onDraw(onDrawListener)
////                .onLoad(onLoadCompleteListener)
////                .onPageChange(onPageChangeListener)
//                .load();
    }
    //http://www.dinh.dk/pdf/badathaygi.pdf
}

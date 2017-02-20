package com.bongtran.pdfreader;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bongtran.pdfreader.fileUtils.FileUtils;
import com.joanzapata.pdfview.PDFView;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Main3Activity extends AppCompatActivity {

    private PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
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

        pdfView = (PDFView)findViewById(R.id.pdfview);
        File file = null;
        try {
            URL url = new URL("http://www.dinh.dk/pdf/badathaygi.pdf");
            FileUtils.getOutputMediaFile("");
            pdfView.fromFile(file)
                    .pages(0, 2, 1, 3, 3, 3)
                    .defaultPage(1)
                    .showMinimap(false)
                    .enableSwipe(true)
//                .onDraw(onDrawListener)
//                .onLoad(onLoadCompleteListener)
//                .onPageChange(onPageChangeListener)
                    .load();
        }
//        } catch (URISyntaxException e) {
//        e.printStackTrace();
//    }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}

package com.bongtran.ntc.activity;

import com.bongtran.ntc.R;
import com.bongtran.ntc.adapter.SongAdapter;
import com.bongtran.ntc.app.SongApp;
import com.bongtran.ntc.database.DataManager;
import com.bongtran.ntc.model.SongModel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int mLevel = 0;
    private EditText txtSearch;
    private ListView listView;
    private SongAdapter songAdapter;
    private SongAdapter.SongListener songListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the text view to show the level number.
        txtSearch = (EditText) findViewById(R.id.txt_searchText);

        listView = (ListView) findViewById(R.id.lvResult);

        songAdapter = new SongAdapter(getApplication(), R.layout.view_item_song);
        songListener = new SongAdapter.SongListener() {
            @Override
            public void onClick(int position, SongModel object) {
                loadPDFFile(object);
            }
        };
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0)
                    loadSongs(charSequence.toString());
                else
                    loadSongs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        songAdapter.setTaskListener(songListener);
        listView.setAdapter(songAdapter);
        loadSongs();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadSongs() {
        ArrayList<SongModel> songModels = DataManager.sharedInstance().getSongs();
        songAdapter.addAll(songModels);
    }

    private void loadSongs(String search) {
        ArrayList<SongModel> songModels = DataManager.sharedInstance().getSongs(search);
        songAdapter.addAll(songModels);
    }

    private void loadPDFFile(SongModel song) {
        if (!song.getUrl1().isEmpty()) {
            mLevel++;
            Intent intent = new Intent(getBaseContext(), PDFWebViewActivity.class);
            intent.putExtra("name", song.getName());
            intent.putExtra("url1", song.getUrl1());
            intent.putExtra("url2", song.getUrl2());
            intent.putExtra("count", mLevel);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }
        else {
            Toast.makeText(SongApp.getAppContext(), "Không thể mở bài nhạc này", Toast.LENGTH_LONG);
        }
    }
}

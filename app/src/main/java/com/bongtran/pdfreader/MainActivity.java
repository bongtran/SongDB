package com.bongtran.pdfreader;

import com.bongtran.pdfreader.adapter.SongAdapter;
import com.bongtran.pdfreader.database.DataManager;
import com.bongtran.pdfreader.model.SongModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

    private static final int START_LEVEL = 1;
    private int mLevel;
    private Button mNextLevelButton;
    private InterstitialAd mInterstitialAd;
    private EditText txtSearch;
    private ListView listView;
    private SongAdapter songAdapter;
    private SongAdapter.SongListener songListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the next level button, which tries to show an interstitial when clicked.
//        mNextLevelButton = ((Button) findViewById(R.id.next_level_button));
//        mNextLevelButton.setEnabled(false);
//        mNextLevelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showInterstitial();
//            }
//        });

        // Create the text view to show the level number.
        txtSearch = (EditText) findViewById(R.id.txt_searchText);
        listView = (ListView) findViewById(R.id.lvResult);
//        mLevel = START_LEVEL;

        songAdapter = new SongAdapter(getApplication(), R.layout.view_item_song);
        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd();
//        loadInterstitial();
        songListener = new SongAdapter.SongListener() {
            @Override
            public void onClick(int position, SongModel object) {
                loadPDFFile(object);
            }
        };

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

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mNextLevelButton.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                mNextLevelButton.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                goToNextLevel();
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            goToNextLevel();
        }
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.
        mNextLevelButton.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void goToNextLevel() {
        // Show the next level and reload the ad to prepare for the level after.
        txtSearch.setText("Level " + (++mLevel));
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
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
        if(!song.getUrl1().isEmpty()){
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("name", song.getName());
            intent.putExtra("url1", song.getUrl1());
            intent.putExtra("url2", song.getUrl2());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }
    }
}

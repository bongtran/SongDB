package com.bongtran.ntc.database;

import android.content.Context;

import com.bongtran.ntc.app.SongApp;
import com.bongtran.ntc.model.SongModel;

import java.util.ArrayList;

/**
 * Created by ASUS on 2/21/2017.
 */

public class DataManager {
    static DataManager _instance;
    static Context _context;
    final static String _lock = "";

    DataManager() {
    }

    public static DataManager sharedInstance() {
        synchronized (_lock) {
            if (_instance == null) {
                _instance = new DataManager();
                initDataManager(SongApp.getAppContext());
            }
            return _instance;
        }
    }

    public static void initDataManager(Context applicationContext) {
        _context = applicationContext;
    }

    public ArrayList<SongModel> getSongs() {
        SongDataSource sbContact = new SongDataSource(_context);
        sbContact.open();
        ArrayList<SongModel> result = sbContact.get100Songs();
        sbContact.close();
        sbContact = null;
        return result;
    }

    public ArrayList<SongModel> getSongs(String search) {
        SongDataSource sbContact = new SongDataSource(_context);
        sbContact.open();
        ArrayList<SongModel> result = sbContact.getSongs(search);
        sbContact.close();
        sbContact = null;
        return result;
    }
}

package com.bongtran.pdfreader.database;

import android.content.Context;
import android.database.Cursor;

import com.bongtran.pdfreader.model.SongModel;

import java.util.ArrayList;

/**
 * Created by ASUS on 2/21/2017.
 */

public class SongDataSource extends DatasourceBase{
    public SongDataSource(Context context) {
        super(context);
    }

//    static String[] ALL_COLUMN = {
//            SBDatabaseHelper.CONTACT_NAME,
//            SBDatabaseHelper.CONTACT_NUMBER,
//            SBDatabaseHelper.EXITS_USER
//    };

    public ArrayList<SongModel> get100Songs() {
        ArrayList<SongModel> mContact = new ArrayList<SongModel>();
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_SONG + " Limit 100";
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SongModel result = new SongModel(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.SONG_ID_COL)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.SONG_NAME_COL)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.SONG_URL1_COL)));
                mContact.add(result);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return mContact;
    }

    public ArrayList<SongModel> getSongs(String search) {
        ArrayList<SongModel> mContact = new ArrayList<SongModel>();
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_SONG +
                "WHERE " + DatabaseHelper.SONG_NAME_COL + "LIKE '%" + search + "%'"+
                "OR " + DatabaseHelper.SONG_NAME_NON_COL + "LIKE '%" + search + "%'"+
                "ORDER BY ID ASC Limit 100";
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SongModel result = new SongModel(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.SONG_ID_COL)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.SONG_NAME_COL)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.SONG_URL1_COL)));
                mContact.add(result);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return mContact;
    }
}

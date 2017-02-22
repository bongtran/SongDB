package com.bongtran.ntc.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by ASUS on 2/21/2017.
 */

public class SongApp extends Application {
    private static Context appContext;

    public static Context getAppContext() {
        return appContext;
    }

    public static void setAppContext(Context appContext) {
        SongApp.appContext = appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        setAppContext(getApplicationContext());
    }
}

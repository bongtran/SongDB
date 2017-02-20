package com.bongtran.pdfreader.fileUtils;

import android.app.Application;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ASUS on 2/15/2017.
 */

public class FileUtils {
    public static File getOutputMediaFile(String url){
// To be safe, you should check that the SDCard is mounted
// using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + "com.bongtran.pdfreader"
                + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name
        String fileName = url.substring(url.lastIndexOf('/') + 1);
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + fileName);
        if(!mediaFile.exists()){

        }
        return mediaFile;
    }

    public void downloadFileFromUrl(){

    }
}

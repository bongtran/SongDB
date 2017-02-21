package com.bongtran.pdfreader.model;

/**
 * Created by ASUS on 2/21/2017.
 */

public class SongModel {
    private int id;
    private int type;
    private String name;
    private String name_non;
    private String fileName;
    private String startWith;
    private String beginWith;
    private String url1;
    private String url2;
    private String composer;

    public SongModel() {
        this.beginWith = "";
        this.composer = "";
        this.fileName = "";
        this.id = 0;
        this.name = "";
        this.name_non = "";
        this.startWith = "";
        this.type = 0;
        this.url1 = "";
        this.url2 = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_non() {
        return name_non;
    }

    public void setName_non(String name_non) {
        this.name_non = name_non;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStartWith() {
        return startWith;
    }

    public void setStartWith(String startWith) {
        this.startWith = startWith;
    }

    public String getBeginWith() {
        return beginWith;
    }

    public void setBeginWith(String beginWith) {
        this.beginWith = beginWith;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }
}

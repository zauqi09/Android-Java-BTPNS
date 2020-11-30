package com.example.tugas2android.model;

import android.os.Parcel;


public class Berita{
    private int id;
    private String title;
    private String category;
    private String urlpict;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String tittle) {
        this.title = tittle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrlpict() {
        return urlpict;
    }

    public void setUrlpict(String urlpict) {
        this.urlpict = urlpict;
    }
}

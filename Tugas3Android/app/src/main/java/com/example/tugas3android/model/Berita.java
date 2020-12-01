package com.example.tugas3android.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Berita {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("url")
    @Expose
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

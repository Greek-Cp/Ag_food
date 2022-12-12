package com.example.agfood.Model;

import com.google.gson.annotations.SerializedName;

public class ModelResponseUpload {
    int kode;
    String message;
    String link;

    public ModelResponseUpload(int kode, String message, String link) {
        this.kode = kode;
        this.message = message;
        this.link = link;
    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

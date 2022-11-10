package com.example.agfood.Model;

import java.util.List;

public class ModelResponseAccount {
    private int kode;
    private String pesan;
    private List<ModelAccount> data;

    public ModelResponseAccount(int kode, String pesan, List<ModelAccount> data) {
        this.kode = kode;
        this.pesan = pesan;
        this.data = data;
    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<ModelAccount> getData() {
        return data;
    }

    public void setData(List<ModelAccount> data) {
        this.data = data;
    }
}

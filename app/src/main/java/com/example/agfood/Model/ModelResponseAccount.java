package com.example.agfood.Model;

import java.util.ArrayList;
import java.util.List;

public class ModelResponseAccount {
    public int kode;
    public String pesan;
    public ArrayList<ModelRetrieveAccount> data;

    public ModelResponseAccount(int kode, String pesan, ArrayList<ModelRetrieveAccount> data) {
        this.kode = kode;
        this.pesan = pesan;
        this.data = data;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public ArrayList<ModelRetrieveAccount> getData() {
        return data;
    }

    public void setData(ArrayList<ModelRetrieveAccount> data) {
        this.data = data;
    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }
}

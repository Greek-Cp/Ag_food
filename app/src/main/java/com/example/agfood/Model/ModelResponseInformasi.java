package com.example.agfood.Model;

import com.google.gson.annotations.SerializedName;

public class ModelResponseInformasi {
    private int kode;
    private String pesan;
    @SerializedName("informasi")
    private String informasi;

    public ModelResponseInformasi(int kode, String pesan, String informasi) {
        this.kode = kode;
        this.pesan = pesan;
        this.informasi = informasi;
    }
    public ModelResponseInformasi(){

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

    public String getInformasi() {
        return informasi;
    }

    public void setInformasi(String informasi) {
        this.informasi = informasi;
    }
}

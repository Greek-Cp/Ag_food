package com.example.agfood.Model;

import com.google.gson.annotations.SerializedName;

public class ModelResponseGetCurrentIdBarang {
    @SerializedName("kode")
    private int kode;
    @SerializedName("id_keranjang")
    private String idKeranjang;
    @SerializedName("pesan")
    private String pesan;

    public ModelResponseGetCurrentIdBarang(int kode, String idKeranjang, String pesan) {
        this.kode = kode;
        this.idKeranjang = idKeranjang;
        this.pesan = pesan;
    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getIdKeranjang() {
        return idKeranjang;
    }

    public void setIdKeranjang(String idKeranjang) {
        this.idKeranjang = idKeranjang;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}

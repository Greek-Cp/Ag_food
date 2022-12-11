package com.example.agfood.Model;

import java.util.List;

public class ModelResponseIdKeranjang {

     int kode;
     String pesan;
     List<ModelIdKeranjang> dataKeranjang;

    public ModelResponseIdKeranjang(int kode, String pesan, List<ModelIdKeranjang> dataKeranjang) {
        this.kode = kode;
        this.pesan = pesan;
        this.dataKeranjang = dataKeranjang;
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
    public List<ModelIdKeranjang> getDataKeranjang() {
        return dataKeranjang;
    }
    public void setDataKeranjang(List<ModelIdKeranjang> dataKeranjang) {
        this.dataKeranjang = dataKeranjang;
    }
}


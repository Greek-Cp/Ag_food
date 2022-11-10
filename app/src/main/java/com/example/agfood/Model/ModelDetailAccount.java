package com.example.agfood.Model;

import com.google.gson.annotations.SerializedName;

public class ModelDetailAccount {

    @SerializedName("nama_user")
    private String namaLengkap;
    private String noHp;
    private String alamat;
    private String email;

    public ModelDetailAccount(String namaLengkap, String noHp, String alamat) {
        this.namaLengkap = namaLengkap;
        this.noHp = noHp;
        this.alamat = alamat;
    }


    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}

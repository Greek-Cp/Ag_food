package com.example.agfood.Model;

import com.google.gson.annotations.SerializedName;

public class ModelDetailAccount {

    @SerializedName("id_akun")
    int id_akun;
    @SerializedName("nama_user")
     String namaLengkap;
     String noHp;
     String alamat;

    public ModelDetailAccount(String namaLengkap, String noHp, String alamat) {
        this.namaLengkap = namaLengkap;
        this.noHp = noHp;
        this.alamat = alamat;
    }
    public ModelDetailAccount(int id_akun, String namaLengkap, String noHp, String alamat) {
        this.id_akun = id_akun;
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

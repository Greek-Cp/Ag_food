package com.example.agfood.Model;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Field;

public class ModelAccount {
    private String username;
    private String email;
    @SerializedName("password_akun")
    private String password;
    private String kedudukan = "user";
    private String namaLengkap;
    private String noHP;
    private String alamat;

    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getKedudukan() {
        return kedudukan;
    }

    public void setKedudukan(String kedudukan) {
        this.kedudukan = kedudukan;
    }


    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public ModelAccount(String username, String email, String password, String kedudukan) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.kedudukan = kedudukan;
    }

    public ModelAccount(String username, String password, String kedudukan , String email , String namaLengkap , String noHp, String alamat) {
        this.username = username;
        this.password = password;
        this.kedudukan = kedudukan;
        this.email = email;
        this.namaLengkap = namaLengkap;
        this.noHP = noHp;
        this.alamat = alamat;
    }
}

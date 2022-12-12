package com.example.agfood.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.http.Field;

public class ModelAccount {
    @SerializedName("id_akun")
    @Expose
    public String idAkun;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("kedudukan")
    @Expose
    public String kedudukan;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("verify_number")
    @Expose
    public String verifyNumber;
    @SerializedName("status_verif")
    @Expose
    public String statusVerif;
    @SerializedName("alamat")
    @Expose
    public String alamat;
    @SerializedName("nama_user")
    @Expose
    public String namaLengkap;
    @SerializedName("noHp")
    @Expose
    public String noHp;

    public ModelAccount() {
    }
    public ModelAccount(String username, String email, String password, String kedudukan) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.kedudukan = kedudukan;
    }
    public ModelAccount(String idAkun, String username, String password, String kedudukan, String email, String verifyNumber, String statusVerif, String alamat, String namaUser, String noHp) {
        super();
        this.idAkun = idAkun;
        this.username = username;
        this.password = password;
        this.kedudukan = kedudukan;
        this.email = email;
        this.verifyNumber = verifyNumber;
        this.statusVerif = statusVerif;
        this.alamat = alamat;
        this.namaLengkap = namaUser;
        this.noHp = noHp;
    }

    public String getIdAkun() {
        return idAkun;
    }

    public void setIdAkun(String idAkun) {
        this.idAkun = idAkun;
    }

    public String getUsername() {
        return username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifyNumber() {
        return verifyNumber;
    }

    public void setVerifyNumber(String verifyNumber) {
        this.verifyNumber = verifyNumber;
    }

    public String getStatusVerif() {
        return statusVerif;
    }

    public void setStatusVerif(String statusVerif) {
        this.statusVerif = statusVerif;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
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
}

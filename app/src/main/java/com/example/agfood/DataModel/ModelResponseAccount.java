package com.example.agfood.DataModel;


import com.example.agfood.Model.ModelAccount;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelResponseAccount {
    public int kode;
    public String pesan;
    public int otp;
    @SerializedName("detail_account")
    List<ModelAccount> detail_account;

    public List<ModelAccount> getDetail_account() {
        return detail_account;
    }

    public void setDetail_account(List<ModelAccount> detail_account) {
        this.detail_account = detail_account;
    }

    public ModelResponseAccount(int kode, String pesan, int otp, List<ModelAccount> detail_account) {
        this.kode = kode;
        this.pesan = pesan;
        this.otp = otp;
        this.detail_account = detail_account;
    }

    public ModelResponseAccount(int kode, String pesan) {
        this.kode = kode;
        this.pesan = pesan;

    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }



    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }
}

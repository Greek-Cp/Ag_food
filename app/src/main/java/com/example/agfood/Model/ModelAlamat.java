package com.example.agfood.Model;

import com.google.gson.annotations.SerializedName;

public class ModelAlamat {
    @SerializedName("alamat")
    private String alamat;
    private boolean isAlamatSelected = false;
    public ModelAlamat(String alamat, boolean isAlamatSelected) {
        this.alamat = alamat;
        this.isAlamatSelected = isAlamatSelected;
    }

    public ModelAlamat(String alamat) {
        this.alamat = alamat;
    }

    public boolean isAlamatSelected() {
        return isAlamatSelected;
    }

    public void setAlamatSelected(boolean alamatSelected) {
        isAlamatSelected = alamatSelected;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}

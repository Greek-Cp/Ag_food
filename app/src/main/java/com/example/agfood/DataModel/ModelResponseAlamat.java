package com.example.agfood.DataModel;

import com.example.agfood.Model.ModelAlamat;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelResponseAlamat {
    private int kode;
    private String pesan;
    @SerializedName("alamat_saya")
    private List<ModelAlamat> modelAlamatList;

    public ModelResponseAlamat(int kode, String pesan, List<ModelAlamat> modelAlamatList) {
        this.kode = kode;
        this.pesan = pesan;
        this.modelAlamatList = modelAlamatList;
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

    public List<ModelAlamat> getModelAlamatList() {
        return modelAlamatList;
    }

    public void setModelAlamatList(List<ModelAlamat> modelAlamatList) {
        this.modelAlamatList = modelAlamatList;
    }
}

package com.example.agfood.Model;

public class ModelKategory {
    private String namaKategory;
    private boolean statusClickKategory = false;

    public ModelKategory(String namaKategory, boolean statusClickKategory) {
        this.namaKategory = namaKategory;
        this.statusClickKategory = statusClickKategory;
    }

    public String getNamaKategory() {
        return namaKategory;
    }

    public void setNamaKategory(String namaKategory) {
        this.namaKategory = namaKategory;
    }

    public boolean isStatusClickKategory() {
        return statusClickKategory;
    }

    public void setStatusClickKategory(boolean statusClickKategory) {
        this.statusClickKategory = statusClickKategory;
    }
}

package com.example.agfood.Model;

public class ModelTopping {
    private String namaTopping;
    private int hargaTopping;
    private int satuanTopping;
    private int totalHargaTopping;
    private boolean isCheckboxCliked = false;
    private int imageTopping;

    public int getImageTopping() {
        return imageTopping;
    }

    public void setImageTopping(int imageTopping) {
        this.imageTopping = imageTopping;
    }

    public ModelTopping(String namaTopping, int hargaTopping, int satuanTopping, boolean isCheckboxCliked, int imageTopping) {
        this.namaTopping = namaTopping;
        this.hargaTopping = hargaTopping;
        this.satuanTopping = satuanTopping;
        this.isCheckboxCliked = isCheckboxCliked;
        this.totalHargaTopping = satuanTopping * hargaTopping;
        this.imageTopping = imageTopping;
    }


    public int getTotalHargaTopping() {
        return totalHargaTopping;
    }

    public void setTotalHargaTopping(int totalHargaTopping) {
        this.totalHargaTopping = totalHargaTopping;
    }

    public String getNamaTopping() {
        return namaTopping;
    }
    public void setNamaTopping(String namaTopping) {
        this.namaTopping = namaTopping;
    }

    public int getHargaTopping() {
        return hargaTopping;
    }

    public void setHargaTopping(int hargaTopping) {
        this.hargaTopping = hargaTopping;
    }

    public int getSatuanTopping() {
        return satuanTopping;
    }

    public void setSatuanTopping(int satuanTopping) {
        this.satuanTopping = satuanTopping;
    }

    public boolean isCheckboxCliked() {
        return isCheckboxCliked;
    }

    public void setCheckboxCliked(boolean checkboxCliked) {
        isCheckboxCliked = checkboxCliked;
    }
}

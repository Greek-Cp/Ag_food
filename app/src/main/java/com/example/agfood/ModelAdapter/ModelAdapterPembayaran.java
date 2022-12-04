package com.example.agfood.ModelAdapter;

public class ModelAdapterPembayaran {
private String namaServicePayment;
private int imgServicePayment;
private String akunToko;
private boolean isMetodePesananSelected;

    public ModelAdapterPembayaran(String namaServicePayment, int imgServicePayment, String akunToko
    ,boolean isMetodePesananSelected){
        this.namaServicePayment = namaServicePayment;
        this.imgServicePayment = imgServicePayment;
        this.akunToko = akunToko;
        this.isMetodePesananSelected = isMetodePesananSelected;
    }

    public boolean isMetodePesananSelected() {
        return isMetodePesananSelected;
    }

    public void setMetodePesananSelected(boolean metodePesananSelected) {
        isMetodePesananSelected = metodePesananSelected;
    }

    public String getNamaServicePayment() {
        return namaServicePayment;
    }
    public void setNamaServicePayment(String namaServicePayment) {this.namaServicePayment = namaServicePayment;}
    public int getImgServicePayment() {
        return imgServicePayment;
    }
    public void setImgServicePayment(int imgServicePayment) {this.imgServicePayment = imgServicePayment;}
    public String getAkunToko() {return akunToko;}
    public void setAkunToko(String akunToko) {this.akunToko = akunToko;}
}

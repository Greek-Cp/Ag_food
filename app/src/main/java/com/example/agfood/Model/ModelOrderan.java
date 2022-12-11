package com.example.agfood.Model;

import java.util.ArrayList;
import java.util.List;

public class ModelOrderan {
    private String idkeranjang;
    private List<ModelBarang> listBarangYgDiOrder = new ArrayList<>();
    int totalHargaOrderan;

    public int getTotalHargaOrderan() {
        return totalHargaOrderan;
    }

    public void setTotalHargaOrderan(int totalHargaOrderan) {
        this.totalHargaOrderan = totalHargaOrderan;
    }

    public ModelOrderan(String idkeranjang, List<ModelBarang> listBarangYgDiOrder) {
        this.idkeranjang = idkeranjang;
        this.listBarangYgDiOrder = listBarangYgDiOrder;
    }

    public ModelOrderan(String idkeranjang){
        this.idkeranjang = idkeranjang;
    }
    public String getIdkeranjang() {
        return idkeranjang;
    }

    public void setIdkeranjang(String idkeranjang) {
        this.idkeranjang = idkeranjang;
    }

    public List<ModelBarang> getListBarangYgDiOrder() {
        return listBarangYgDiOrder;
    }

    public void setListBarangYgDiOrder(List<ModelBarang> listBarangYgDiOrder) {
        this.listBarangYgDiOrder = listBarangYgDiOrder;
    }
}

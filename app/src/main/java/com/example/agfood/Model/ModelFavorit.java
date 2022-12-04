package com.example.agfood.Model;

public class ModelFavorit {
    public int id_akun;
    public String id_barang;
    public String status_fav;

    public ModelFavorit(int id_akun, String id_barang, String status_fav) {
        this.id_akun = id_akun;
        this.id_barang = id_barang;
        this.status_fav = status_fav;
    }
    public ModelFavorit(){

    }
    public int getId_akun() {
        return id_akun;
    }
    public void setId_akun(int id_akun) {
        this.id_akun = id_akun;
    }
    public String getId_barang() {
        return id_barang;
    }
    public void setId_barang(String id_barang) {
        this.id_barang = id_barang;
    }
    public String getStatus_fav() {
        return status_fav;
    }
    public void setStatus_fav(String status_fav) {
        this.status_fav = status_fav;
    }
}

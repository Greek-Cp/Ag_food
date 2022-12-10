package com.example.agfood.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelBarang {
    @SerializedName("id_barang")
    private String id_barang;
    @SerializedName("nama_barang")
    private String nama_barang;
    @SerializedName("rating")
    private int rating;
    @SerializedName("jenis_barang")
    private String jenis_barang;
    @SerializedName("harga_total")
    private int harga;
    @SerializedName("harga_original")
    private int hargaOriginal;
    @SerializedName("gambar_barang")
    private String gambar_barang;
    @SerializedName("deskripsi_barang")
    private String deskripsi_barang;
    @SerializedName("imageType")
    private String imageType;
    @SerializedName("total_harga")
    private int totalHarga;
    @SerializedName("total_item_keranjang")
    private int totalItemKeranjang;
    @SerializedName("total_item")
    private int totalItem;
    private boolean status;
    @SerializedName("listKeranjangId")
    private List<String> listIdKeranjang;
    public ModelBarang(){
        
    }

    public List<String> getListIdKeranjang() {
        return listIdKeranjang;
    }

    public void setListIdKeranjang(List<String> listIdKeranjang) {
        this.listIdKeranjang = listIdKeranjang;
    }

    public ModelBarang(String id_barang, String nama_barang, int rating, String jenis_barang, int harga, int hargaOriginal, String gambar_barang, String deskripsi_barang, String imageType, int totalHarga, int totalItemKeranjang, int totalItem, boolean status) {
        this.id_barang = id_barang;
        this.nama_barang = nama_barang;
        this.rating = rating;
        this.jenis_barang = jenis_barang;
        this.harga = harga;
        this.hargaOriginal = hargaOriginal;
        this.gambar_barang = gambar_barang;
        this.deskripsi_barang = deskripsi_barang;
        this.imageType = imageType;
        this.totalHarga = totalHarga;
        this.totalItemKeranjang = totalItemKeranjang;
        this.totalItem = totalItem;
        this.status = status;
    }

    public int getHargaOriginal() {
        return hargaOriginal;
    }

    public void setHargaOriginal(int hargaOriginal) {
        this.hargaOriginal = hargaOriginal;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ModelBarang(String id_barang, String nama_barang, int rating, String jenis_barang, int harga, String gambar_barang, String deskripsi_barang, String imageType, int totalHarga, int totalItemKeranjang, int totalItem, boolean status) {
        this.id_barang = id_barang;
        this.nama_barang = nama_barang;
        this.rating = rating;
        this.jenis_barang = jenis_barang;
        this.harga = harga;
        this.gambar_barang = gambar_barang;
        this.deskripsi_barang = deskripsi_barang;
        this.imageType = imageType;
        this.totalHarga = totalHarga;
        this.totalItemKeranjang = totalItemKeranjang;
        this.totalItem = totalItem;
        this.status = status;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }

    public int getTotalItemKeranjang() {
        return totalItemKeranjang;
    }

    public void setTotalItemKeranjang(int totalItemKeranjang) {
        this.totalItemKeranjang = totalItemKeranjang;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public ModelBarang(String id_barang, String nama_barang, int rating, String jenis_barang, int harga, String gambar_barang, String deskripsi_barang, String imageType) {
        this.id_barang = id_barang;
        this.nama_barang = nama_barang;
        this.rating = rating;
        this.jenis_barang = jenis_barang;
        this.harga = harga;
        this.gambar_barang = gambar_barang;
        this.deskripsi_barang = deskripsi_barang;
        this.imageType = imageType;
    }

    public String getId_barang() {
        return id_barang;
    }

    public void setId_barang(String id_barang) {
        this.id_barang = id_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getJenis_barang() {
        return jenis_barang;
    }

    public void setJenis_barang(String jenis_barang) {
        this.jenis_barang = jenis_barang;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getGambar_barang() {
        return gambar_barang;
    }

    public void setGambar_barang(String gambar_barang) {
        this.gambar_barang = gambar_barang;
    }

    public String getDeskripsi_barang() {
        return deskripsi_barang;
    }

    public void setDeskripsi_barang(String deskripsi_barang) {
        this.deskripsi_barang = deskripsi_barang;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}

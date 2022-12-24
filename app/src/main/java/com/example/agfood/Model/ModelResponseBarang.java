package com.example.agfood.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelResponseBarang {
    @SerializedName("id_akun")
    private int id_akun;
    @SerializedName("id_barang")
    private String id_barang;
    @SerializedName("kode")
    private Integer kode;
    @SerializedName("pesan")
    private String pesan;
    @SerializedName("dataBarang")
    private List<ModelBarang> dataBarang;
    @SerializedName("metode_pembayaran")
    private String metodePembayaran;
    @SerializedName("no_akun")
    private String akunPembayaran;
    @SerializedName("alamat_pengiriman")
    private String alamatPengiriman;
    public String getAlamatPengiriman() {
        return alamatPengiriman;
    }

    public void setAlamatPengiriman(String alamatPengiriman) {
        this.alamatPengiriman = alamatPengiriman;
    }

    public ModelResponseBarang(Integer kode, String pesan, List<ModelBarang> listBarang) {
        this.kode = kode;
        this.pesan = pesan;
        this.dataBarang = listBarang;
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
    public Integer getKode() {
        return kode;
    }
    public void setKode(Integer kode) {
        this.kode = kode;
    }
    public String getPesan() {
        return pesan;
    }
    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
    public List<ModelBarang> getDataBarang() {
        return dataBarang;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public String getAkunPembayaran() {
        return akunPembayaran;
    }

    public void setAkunPembayaran(String akunPembayaran) {
        this.akunPembayaran = akunPembayaran;
    }

    public void setDataBarang(List<ModelBarang> dataBarang) {
        this.dataBarang = dataBarang;
    }
}

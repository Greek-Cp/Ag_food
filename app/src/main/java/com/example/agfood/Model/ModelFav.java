package com.example.agfood.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelFav {
    @SerializedName("id_akun")
    @Expose
    public String idAkun;
    @SerializedName("id_barang")
    @Expose
    public String idBarang;
    @SerializedName("status_fav")
    @Expose
    public String statusFav;

    public ModelFav(String idAkun, String idBarang, String statusFav) {
        this.idAkun = idAkun;
        this.idBarang = idBarang;
        this.statusFav = statusFav;
    }

    public String getIdAkun() {
        return idAkun;
    }

    public void setIdAkun(String idAkun) {
        this.idAkun = idAkun;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getStatusFav() {
        return statusFav;
    }

    public void setStatusFav(String statusFav) {
        this.statusFav = statusFav;
    }
}

package com.example.agfood.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelResponseFav {
    @SerializedName("favorit_akun")
    List<ModelFav> listFav;
    public ModelResponseFav(List<ModelFav> listFav) {
        this.listFav = listFav;
    }
    public List<ModelFav> getListFav() {
        return listFav;
    }

    public void setListFav(List<ModelFav> listFav) {
        this.listFav = listFav;
    }
}

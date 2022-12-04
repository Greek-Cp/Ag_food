package com.example.agfood.Model;

public class ModelBarangWithLike {
    ModelBarang modelBarang;
    ModelFav modelFav;

    public ModelBarangWithLike(ModelBarang modelBarang, ModelFav modelFav) {
        this.modelBarang = modelBarang;
        this.modelFav = modelFav;
    }

    public ModelBarang getModelBarang() {
        return modelBarang;
    }

    public void setModelBarang(ModelBarang modelBarang) {
        this.modelBarang = modelBarang;
    }

    public ModelFav getModelFav() {
        return modelFav;
    }

    public void setModelFav(ModelFav modelFav) {
        this.modelFav = modelFav;
    }
}

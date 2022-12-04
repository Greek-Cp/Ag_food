package com.example.agfood.Model;

import java.util.List;

public class ModelFood {
    private String nameFood;
    private int totalStockFood;
    private int hargaFood;
    private int imageFood;
    private List<ModelTopping> listModelTopping;

    //private String uriImage;
    public ModelFood(String nameFood, int totalStockFood, int hargaFood, int imageFood) {
        this.nameFood = nameFood;
        this.totalStockFood = totalStockFood;
        this.hargaFood = hargaFood;
        this.imageFood = imageFood;
    }

    public ModelFood(String nameFood, int totalStockFood, int hargaFood, int imageFood, List<ModelTopping> listModelTopping) {
        this.nameFood = nameFood;
        this.totalStockFood = totalStockFood;
        this.hargaFood = hargaFood;
        this.imageFood = imageFood;
        this.listModelTopping = listModelTopping;
    }

    public List<ModelTopping> getListModelTopping() {
        return listModelTopping;
    }

    public void setListModelTopping(List<ModelTopping> listModelTopping) {
        this.listModelTopping = listModelTopping;
    }

    public String getNameFood() {
        return nameFood;
    }
    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }
    public int getTotalStockFood() {
        return totalStockFood;
    }
    public void setTotalStockFood(int totalStockFood) {
        this.totalStockFood = totalStockFood;
    }
    public int getHargaFood() {
        return hargaFood;
    }
    public void setHargaFood(int hargaFood) {
        this.hargaFood = hargaFood;
    }
    public int getImageFood() {
        return imageFood;
    }
    public void setImageFood(int imageFood) {
        this.imageFood = imageFood;
    }
}

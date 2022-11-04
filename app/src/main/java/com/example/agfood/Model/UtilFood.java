package com.example.agfood.Model;

import com.example.agfood.R;

import java.util.ArrayList;
import java.util.List;

public class UtilFood {
    public static List<ModelFood> getListCemilan(){
        List<ModelFood> modelFoodList = new ArrayList<>();
        modelFoodList.add(new ModelFood("Siomay Isi 6pcs", 0, 5000, R.drawable.cemilan_somay_assetone));
        modelFoodList.add(new ModelFood("Siomay Isi 13pcs", 0, 10000, R.drawable.cemilan_somay_assetone));
        modelFoodList.add(new ModelFood("Siomay Full Isi 4pcs", 0, 5000, R.drawable.cemilan_somay_assetone));
        modelFoodList.add(new ModelFood("Siomay Full Isi 9pcs", 0, 10000, R.drawable.cemilan_somay_assetone));
        modelFoodList.add(new ModelFood("Burger Chicken", 0, 10000, R.drawable.cemilan_burger_ayam));
        modelFoodList.add(new ModelFood("Burger Chicken Saus Fire", 0, 11000, R.drawable.cemilan_burger_ayam_1));
        modelFoodList.add(new ModelFood("Burger Beef", 0, 9000, R.drawable.cemilan_burger_beef));
        modelFoodList.add(new ModelFood("Burger Beef Saus Fire", 0, 10000, R.drawable.cemilan_burger_beef));
        return modelFoodList;
    }
    public static List<ModelFood> getListMinuman(){
        List<ModelFood> modelFoodList = new ArrayList<>();
        modelFoodList.add(new ModelFood("Teh(Hot/Cold)", 30, 3000, R.drawable.minuman_es_teh));
        modelFoodList.add(new ModelFood("Milk(Hot/Cold)", 30, 4000, R.drawable.minuman_milk_tea));
        modelFoodList.add(new ModelFood("Milo(Hot/Cold)", 30, 5000, R.drawable.minuman_milo));
        return modelFoodList;
    }
    public static List<ModelButton> getListKategoriMenuMakanan(){
        List<ModelButton> listButtonName = new ArrayList<>();
        listButtonName.add(new ModelButton( "Semua", false));
        listButtonName.add(new ModelButton( "Menu Reguler", false));
        listButtonName.add(new ModelButton( "Menu Mendium", false));
        listButtonName.add(new ModelButton( "Menu Large", false));
        listButtonName.add(new ModelButton( "Menu Cemilan", false));
        listButtonName.add(new ModelButton("Menu Minuman", false));
        return listButtonName;
    }
    public static List<ModelFood> getListFood(){
        List<ModelFood> modelFoodList = new ArrayList<>();
        modelFoodList.add(new ModelFood("Pie Basah", 30, 4000, R.drawable.food_img_1));
        modelFoodList.add(new ModelFood("Nasi Goreng Telur", 15, 5000, R.drawable.food_img_2));
        modelFoodList.add(new ModelFood("Pie Sushi", 30, 4000, R.drawable.food_img_3));
        modelFoodList.add(new ModelFood("Hamburger Besar", 20, 6000, R.drawable.food_img_4));
        modelFoodList.add(new ModelFood("Fried Chicken ", 31, 6000, R.drawable.food_img_5));
        modelFoodList.add(new ModelFood("Chicken Katsu", 10, 6000, R.drawable.food_img_6));
        modelFoodList.add(new ModelFood("Nasi Garam", 10, 6000, R.drawable.food_img_7));
        return modelFoodList;
    }
}

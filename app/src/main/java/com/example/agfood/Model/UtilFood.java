package com.example.agfood.Model;

import com.example.agfood.API.APIRequestData;
import com.example.agfood.API.BaseServerApp;
import com.example.agfood.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilFood {

    public static List<ModelFood> getListCemilan(){
        List<ModelFood> modelFoodList = new ArrayList<>();
        List<ModelTopping> listModelToppingSomay = new ArrayList<>();
        listModelToppingSomay.add( new ModelTopping("Isi 6 pcs", 5000, 1, false,R.drawable.ic_somay_1));
        listModelToppingSomay.add( new ModelTopping("Isi 13 pcs", 10000, 1, false,R.drawable.ic_somay_2));
        listModelToppingSomay.add( new ModelTopping("Siomay Full Isi 4 pcs", 5000, 1, false,R.drawable.ic_somay_2));
        listModelToppingSomay.add( new ModelTopping("Siomay Full Isi 9 pcs", 10000, 1, false,R.drawable.ic_somay_1));
        List<ModelTopping> listModelToppingBurgerChicken = new ArrayList<>();
        listModelToppingBurgerChicken.add(new ModelTopping("Burger Chicken", 10000, 1, false,R.drawable.cemilan_burger_ayam));
        listModelToppingBurgerChicken.add(new ModelTopping("Burger Chicken Saus Fire", 11000, 1, false,R.drawable.cemilan_burger_ayam_1));
        List<ModelTopping> listModelToppingBurgerBeef = new ArrayList<>();
        listModelToppingBurgerBeef.add(new ModelTopping("Burger Beef",9000,0,false,R.drawable.cemilan_burger_beef));
        listModelToppingBurgerBeef.add(new ModelTopping("Burger Beef Saus Fire",10000,0,false,R.drawable.cemilan_burger_beef));
        modelFoodList.add(new ModelFood("Siomay", 0, 0, R.drawable.cemilan_somay_assetone,listModelToppingSomay));
        modelFoodList.add(new ModelFood("Burger Chicken", 0, 0, R.drawable.cemilan_burger_ayam,listModelToppingBurgerChicken));
        modelFoodList.add(new ModelFood("Burger Beef", 0, 0, R.drawable.cemilan_burger_beef,listModelToppingBurgerBeef));
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
        listButtonName.add(new ModelButton( "Populer", true,R.drawable.ic_category_popular));
        listButtonName.add(new ModelButton("Makanan", false,R.drawable.ic_category_makanan));
       /*
        listButtonName.add(new ModelButton( "Menu Reguler", false));
        listButtonName.add(new ModelButton( "Menu Mendium", false));
        listButtonName.add(new ModelButton( "Menu Large", false));
        listButtonName.add(new ModelButton( "Menu Cemilan", false));
        */
        listButtonName.add(new ModelButton("Minuman", false,R.drawable.ic_category_minuman));
        return listButtonName;
    }
    public static List<ModelFood> getListFood(){
        List<ModelFood> modelFoodList = new ArrayList<>();
        modelFoodList.add(new ModelFood("Pie Basah", 30, 4000, R.drawable.food_img_1));
        modelFoodList.add(new ModelFood("Nasi Goreng Telur", 15, 5000, R.drawable.food_img_2));
        modelFoodList.add(new ModelFood("Pie Sushi", 30, 4000, R.drawable.food_img_3));
        modelFoodList.add(new ModelFood("Hamburger Besar", 20, 0, R.drawable.food_img_4));
        modelFoodList.add(new ModelFood("Fried Chicken ", 31, 0, R.drawable.food_img_5));
        modelFoodList.add(new ModelFood("Chicken Katsu", 10, 0, R.drawable.food_img_6));
        modelFoodList.add(new ModelFood("Nasi Garam", 10, 6000, R.drawable.food_img_7));
        return modelFoodList;
    }
    static List<ModelFav> modelFavorits = new ArrayList<>();
    static List<ModelFav> listFav;
    public static List<ModelFav> getListFoodLikeByUser(String id_akun){
        APIRequestData apiRequestData = BaseServerApp.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponseFav> modelFavoritCall = apiRequestData.getListiFavoritAccount(id_akun);
         listFav = new ArrayList<>();
        modelFavoritCall.enqueue(new Callback<ModelResponseFav>() {
            @Override
            public void onResponse(Call<ModelResponseFav> call, Response<ModelResponseFav> response) {
                listFav = response.body().getListFav();
                System.out.println(new Gson().toJson(response.body()) + " GSON DATA");
            }

            @Override
            public void onFailure(Call<ModelResponseFav> call, Throwable t) {
               System.out.println(t.getMessage() + " ERRROR ");
            }
        });
        return listFav;
    }
 static List<ModelBarang> listBarang;
    public static List<ModelBarang> getListFoodAPI(){
        APIRequestData apiRequestData = BaseServerApp.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponseBarang> modelResponseBarangCall  = apiRequestData.getResponseDataBarang();
        modelResponseBarangCall.enqueue(new Callback<ModelResponseBarang>() {
            @Override
            public void onResponse(Call<ModelResponseBarang> call, Response<ModelResponseBarang> response) {
                int kode = response.body().getKode().intValue();
                listBarang = response.body().getDataBarang();
            }
            @Override
            public void onFailure(Call<ModelResponseBarang> call, Throwable t) {
                System.out.println("DATA"   +t.getMessage());
            }
        });
        return listBarang;
    }
}

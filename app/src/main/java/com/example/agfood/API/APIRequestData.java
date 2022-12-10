package com.example.agfood.API;

import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelBarang;
import com.example.agfood.Model.ModelFavorit;
import com.example.agfood.DataModel.ModelResponseAccount;
import com.example.agfood.Model.ModelResponseBarang;
import com.example.agfood.Model.ModelResponseFav;
import com.example.agfood.Model.ModelResponseGetCurrentIdBarang;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIRequestData {
    @Headers({
            "Content-Type: application/json;charset=utf-8",
            "Accept: application/json"
    })
    @GET("retrive_lovelist.php?")
    Call<ModelResponseFav> getListiFavoritAccount(@Query("id_akun") int idAkun);

    @Headers({
            "Content-Type: application/json;charset=utf-8",
            "Accept: application/json"
    })
    @GET("retrieve.php")
    Call<ModelResponseAccount> ardRetriveDataAccount();

    @Headers({
            "Content-Type: application/json;charset=utf-8",
            "Accept: application/json"
    })
    @GET("retrieve_barang.php")
    Call<ModelResponseBarang> getResponseDataBarang();

    @GET("get_current_id_order.php")
    Call<ModelResponseGetCurrentIdBarang> getListIdKeranjang();

    @FormUrlEncoded
    @POST("verify_email.php")
    Call<ModelResponseAccount> validateEmail(@Field("email") String email);

    @FormUrlEncoded
    @POST("getotp.php")
    Call<ModelResponseAccount> getOtp(@Field("email") String email);

    @FormUrlEncoded
    @POST("update_verif.php")
    Call<ModelResponseAccount> verifyAccount(@Field("email") String email);

    @FormUrlEncoded
    @POST("update_password.php")
    Call<ModelResponseAccount> updatePassword(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponseAccount> createAccount(@Field("username") String username,
                                      @Field("password_akun") String password ,
                                      @Field("kedudukan") String kedudukan,
                                      @Field("email") String email,
                                      @Field("alamat") String alamat,
                                      @Field("namaLengkap") String namaUser,
                                      @Field("noHp") String noHp
    );

    @FormUrlEncoded
    @POST("keranjang_user.php")
    Call<ModelResponseBarang> getKeranjangUser(@Field("id_akun") String id_akun);

    @FormUrlEncoded
    @POST("tambah_keranjang.php")
    Call<ModelResponseBarang> savePesananKeKeranjang(@Field("id_akun") String id_akun,
                                                     @Field("id_barang") String id_barang,
                                                     @Field("total_harga")  String totalHarga,
                                                     @Field("total_item") String total_item);

    @FormUrlEncoded
    @POST("hapus_keranjang.php")
    Call<ModelResponseBarang> pindahPesananKeOrderPending(@Field("id_akun") String id_akun,
                                                          @Field("id_barang") String id_barang,
                                                           @Field("total_harga")  String totalHarga,
                                                          @Field("total_item") String total_item,
                                                          @Field("id_keranjang") String id_keranjang);
    
    @FormUrlEncoded
    @POST("login_account.php")
    Call<ModelResponseAccount> loginAccount(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("likefav.php")
    Call<ModelFavorit> saveLikeData(@Field("id_akun") int id_akun,@Field("id_barang")String id_barang,@Field("status_fav") String status_fav);


}

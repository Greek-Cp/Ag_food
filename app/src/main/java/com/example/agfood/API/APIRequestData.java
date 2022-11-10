package com.example.agfood.API;

import com.example.agfood.Model.ModelResponseAccount;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {

    @GET("retrieve.php")
    Call<ModelResponseAccount> ardRetriveDataAccount();

    @FormUrlEncoded
    @POST("create.php")

    Call<ModelResponseAccount> arcReateData(
            @Field("username") String username
            ,@Field("password") String password ,
            @Field("kedudukan") String kedudukan ,
            @Field("alamat") String alamat,
            @Field("nama_user") String namaUser,
            @Field("noHp") String noHp ,
            @Field("email") String email);

}
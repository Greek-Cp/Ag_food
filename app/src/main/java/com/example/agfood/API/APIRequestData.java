package com.example.agfood.API;

import com.example.agfood.DataModel.ModelResponseAlamat;
import com.example.agfood.Model.ModelAccount;
import com.example.agfood.Model.ModelBarang;
import com.example.agfood.Model.ModelFavorit;
import com.example.agfood.DataModel.ModelResponseAccount;
import com.example.agfood.Model.ModelResponseBarang;
import com.example.agfood.Model.ModelResponseFav;
import com.example.agfood.Model.ModelResponseGetCurrentIdBarang;
import com.example.agfood.Model.ModelResponseIdKeranjang;
import com.example.agfood.Model.ModelResponseUpload;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIRequestData {
    @FormUrlEncoded
    @POST("retrive_lovelist.php")
    Call<ModelResponseFav> getListiFavoritAccount(@Field("id_akun") String id_akun);

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
    @GET("retrieve_barang_topping.php")
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
                                                     @Field("total_item") String total_item,
                                                     @Field("pesan_user") String pesan_user);
    @FormUrlEncoded
    @POST("hapus_keranjang.php")
    Call<ModelResponseBarang> pindahPesananKeOrderPending(@Field("id_akun") String id_akun,
                                                          @Field("id_barang") String id_barang,
                                                           @Field("total_harga")  String totalHarga,
                                                          @Field("total_item") String total_item,
                                                          @Field("id_keranjang") String id_keranjang,
                                                          @Field("metode_pembayaran") String metode_pembayaran,
                                                          @Field("no_akun") String no_akun,
                                                          @Field("pesan_user") String pesan_user,
                                                          @Field("alamat_user") String alamat_user
    );


    @FormUrlEncoded
    @POST("tambah_alamat.php")
    Call<ModelResponseAlamat> tambahAlamat(@Field("id_akun") String id_akun,
                                           @Field("alamat") String alamat);

    @FormUrlEncoded
    @POST("hapus_alamat.php")
    Call<ModelResponseAlamat> hapusAlamat(@Field("id_akun") String id_akun,
                                           @Field("alamat") String alamat);

    @FormUrlEncoded
    @POST("get_alamat.php")
    Call<ModelResponseAlamat> getAlamatList(@Field("id_akun") String id_akun);

    @FormUrlEncoded
    @POST("get_id_keranjang.php")
    Call<ModelResponseIdKeranjang> getListIdKeranjang(
            @Field("id_akun") String id_akun
    );

    @FormUrlEncoded
    @POST("get_menu_spesifik.php")
    Call<ModelResponseBarang> getMenuSpesifik(
            @Field("menu_cari") String menu_cari
    );


    @FormUrlEncoded
    @POST("cari_makanan.php")
    Call<ModelResponseBarang> cariMakanan(
            @Field("menu_cari") String menu_cari
    );

    @FormUrlEncoded
    @POST("get_order_list_byuser.php")
    Call<ModelResponseBarang> getListOrderByAccount(@Field("id_akun") String id_akun);

    @FormUrlEncoded
    @POST("login_account.php")
    Call<ModelResponseAccount> loginAccount(@Field("email") String email,
                                    @Field("password") String password);
    @FormUrlEncoded
    @POST("update_status_login.php")
    Call<ModelResponseAccount> logoutSession(@Field("email") String email);

    @FormUrlEncoded
    @POST("update_image_profile_user.php")
    Call<ModelResponseAccount> updateProfilePicture(@Field("email") String email , @Field("image_profile") String imageLink);

    @FormUrlEncoded
    @POST("update_nama_lengkap_user.php")
    Call<ModelResponseAccount> updateNamaLengkap(@Field("id_akun") String email , @Field("nama_lengkap") String namaLengkap);

    @FormUrlEncoded
    @POST("get_image_profile_user_by_email.php")
    Call<ModelResponseAccount> getImageProfile(@Field("email") String email);

    @FormUrlEncoded
    @POST("hapus_keranjang_user.php")
    Call<ModelResponseAccount> hapusKeranjangYangDipilih(@Field("id_akun") String id_akun,
                                            @Field("id_barang") String id_barang);
    @FormUrlEncoded
    @POST("get_nama_lengkap.php")
    Call<ModelResponseAccount> getNamaLengkap(@Field("id_akun") String id_akun);

    @Multipart
    @POST("upload_image.php")
    Call<ModelResponseUpload> uploadImage(@Part MultipartBody.Part image , @Part("file") RequestBody name);

    @Multipart
     @POST("upload_image_transaksi.php")
    Call<ModelResponseUpload> uploadImageTransaksi(@Part MultipartBody.Part image , @Part("file") RequestBody name) ;

    @FormUrlEncoded
    @POST("update_transaksi_image.php")
    Call<ModelResponseAccount> updateImageTransaksi(@Field("id_transaksi") String id_transaksi , @Field("image_transaksi") String imageLink);
    @FormUrlEncoded
    @POST("likefav.php")
    Call<ModelFavorit> saveLikeData(@Field("id_akun") int id_akun,@Field("id_barang")String id_barang,@Field("status_fav") String status_fav);
}

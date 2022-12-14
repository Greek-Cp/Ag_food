package com.example.agfood.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Duration;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class BaseServerApp {
    private static final String baseURL = "http://agfood.kencang.id/";
    private static Retrofit retro;
    private static OkHttpClient.Builder builder = new OkHttpClient.Builder();
    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    public static Retrofit konekRetrofit(){
        if(retro == null){
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor).callTimeout(Duration.ofSeconds(60)).connectTimeout(Duration.ofSeconds(60));
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
                retro = new Retrofit.Builder().baseUrl(baseURL)
                        .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retro;
    }
}

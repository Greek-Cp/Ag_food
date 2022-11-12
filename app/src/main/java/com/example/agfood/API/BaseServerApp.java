package com.example.agfood.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseServerApp {
    private static final String baseURL = "http://172.20.10.4/AG-FOOD/";
    private static Retrofit retro;

    public static Retrofit konekRetrofit(){
        if(retro == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retro = new Retrofit.Builder().baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retro;
    }
}


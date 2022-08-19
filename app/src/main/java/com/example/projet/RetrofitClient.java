package com.example.projet;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String base_url = "http://192.168.1.107/php_project/";
    private static Retrofit retrofit = null;

    public static ApiInterface getRetrofitClient(){
        if(retrofit==null){
            retrofit= new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit.create(ApiInterface.class);
    }
}
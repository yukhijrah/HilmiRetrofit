package com.example.hilmi.portal_ti16.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HTTP;

/**
 * Created by hilmi on 26/11/18.
 */

public class network {

    public static Retrofit request() {
        //instance instercaptor dengan cara
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //setiap ada request ke network, kita monitory bodynya dengan cara
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //bikin client agar bisa menggunakan interceptor
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


            return new Retrofit.Builder()
                    //ini main url dari web services yang tersedia
                    .baseUrl("http://35.186.145.167:1337/")
                    //tambah client okhttp
                    .client(client)
                    //ini melakukan konversi dari json string ke java object
                    .addConverterFactory(GsonConverterFactory.create())
                    //Build it
                    .build();
    }
}

package com.example.hilmi.portal_ti16.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hilmi on 26/11/18.
 */

public class network {

    public static Retrofit request() {
            return new Retrofit.Builder()
                    .baseUrl("https://ti16.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }
}

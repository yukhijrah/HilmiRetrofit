package com.example.hilmi.portal_ti16.network;

import com.example.hilmi.portal_ti16.entity.DaftarMahasiswa;
import com.example.hilmi.portal_ti16.entity.Mahasiswa;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by hilmi on 26/11/18.
 */

public interface Routes {
    @GET("list.php")
    Call<DaftarMahasiswa> getMahasiswa();

    @POST("add.php")
    @FormUrlEncoded
    Call<Mahasiswa> postMahasiswa(
        @Field("name") String name,
        @Field("nim") String nim
    );
}

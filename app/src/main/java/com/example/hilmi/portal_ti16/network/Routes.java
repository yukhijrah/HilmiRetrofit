package com.example.hilmi.portal_ti16.network;

import com.example.hilmi.portal_ti16.entity.DaftarMahasiswa;
import com.example.hilmi.portal_ti16.entity.Mahasiswa;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by hilmi on 26/11/18.
 */

public interface Routes {
    @GET("dev/list_mahasiswa")
    Call<DaftarMahasiswa> getMahasiswa();

    @POST("dev/insert_mahasiswa")
    @FormUrlEncoded
    Call<Mahasiswa> postMahasiswa(
        @Field("name") String name,
        @Field("nim") String nim
    );

    @DELETE("mahasiswatest/{mhsId}")
    Call<Mahasiswa> deleteMahasiswa(
            @Path("mhsId") String mhsId
    );

    /**
     * untuk memperbaharui data mahasiswa
     */
    @PUT("mahasiswatest/{}mhsId")
    Call<Mahasiswa>updateMahasiswa(
      @Path("mhsId") String mhsId,
      @Field("name") String name,
      @Field("nim") String nim
    );

}

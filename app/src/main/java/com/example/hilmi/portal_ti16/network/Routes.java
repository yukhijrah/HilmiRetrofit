package com.example.hilmi.portal_ti16.network;

import com.example.hilmi.portal_ti16.entity.DaftarMahasiswa;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hilmi on 26/11/18.
 */

public interface Routes {
    @GET("list.php")
    Call<DaftarMahasiswa> getMahasiswa();
}

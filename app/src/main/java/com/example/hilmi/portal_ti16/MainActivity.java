package com.example.hilmi.portal_ti16;

import android.net.Network;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;

import com.example.hilmi.portal_ti16.adapter.MahasiswaAdapter;
import com.example.hilmi.portal_ti16.entity.DaftarMahasiswa;
import com.example.hilmi.portal_ti16.network.Routes;
import com.example.hilmi.portal_ti16.network.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hilmi on 26/11/18.
 */

public class MainActivity extends AppCompatActivity {

    RecyclerView listMahasiswa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        listMahasiswa = (RecyclerView)findViewById(R.id.list_mahasiswa);
        listMahasiswa.setLayoutManager(new LinearLayoutManager(this));

        requestDaftarMahasiswa();
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestDaftarMahasiswa();
    }

    private void requestDaftarMahasiswa(){
        Routes Services = network.request().create(Routes.class);

        Services.getMahasiswa().enqueue(new Callback<DaftarMahasiswa>() {
            @Override
            public void onResponse(Call<DaftarMahasiswa> call, Response<DaftarMahasiswa> response) {
                if (response.isSuccessful()){
                    DaftarMahasiswa mahasiswa = response.body();

                    Log.d("Hilmi", mahasiswa.getTitle());

                    MahasiswaAdapter adapter = new MahasiswaAdapter(mahasiswa.getData());
                    listMahasiswa.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<DaftarMahasiswa> call, Throwable t) {

            }
        });
    }
}

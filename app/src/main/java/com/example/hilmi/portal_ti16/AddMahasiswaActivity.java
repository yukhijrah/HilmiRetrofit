package com.example.hilmi.portal_ti16;

import android.net.Network;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hilmi.portal_ti16.entity.Mahasiswa;
import com.example.hilmi.portal_ti16.network.Routes;
import com.example.hilmi.portal_ti16.network.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMahasiswaActivity extends AppCompatActivity {

    private EditText edtName, edtNim;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mahasiswa);

        edtName = (EditText) findViewById(R.id.edt_name);
        edtNim = (EditText) findViewById(R.id.edt_nim);
        btnAdd = (Button) findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String nim = edtNim.getText().toString();
                addNewMahasiswa(name,nim);

            }
        });
    }

    private void addNewMahasiswa(String name, String nim){
        Routes services  = network.request().create(Routes.class);
        //melakukan post terhadap data mahasiswa baru dari API add.php

        services.postMahasiswa(name,nim).enqueue(new Callback<Mahasiswa>() {
            @Override
            public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
             if (response.isSuccessful()){
                 finish();
             }else {
                 Toast.makeText(AddMahasiswaActivity.this,"maaf, terjadi kesalahan", Toast.LENGTH_LONG).show();
             }
            }

            @Override
            public void onFailure(Call<Mahasiswa> call, Throwable t) {
                Toast.makeText(AddMahasiswaActivity.this,"maaf, terjadi kesalahan", Toast.LENGTH_LONG).show();
            }
        });
    }
}

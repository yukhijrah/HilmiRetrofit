package com.example.hilmi.portal_ti16;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hilmi.portal_ti16.entity.Mahasiswa;
import com.example.hilmi.portal_ti16.network.Routes;
import com.example.hilmi.portal_ti16.network.network;
import com.example.hilmi.portal_ti16.util.Consts;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMahasiswaActivity extends AppCompatActivity {

    private EditText edtName, edtNim;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mahasiswa);

        edtName = (EditText) findViewById(R.id.edt_name);
        edtNim = (EditText) findViewById(R.id.edt_nim);
        btnAdd = (Button) findViewById(R.id.btn_add);

        String action = getIntent().getStringExtra(Consts.KEY_ACTION_DETAIL);
        switch (action){
            case Consts.INTENT_ADD:
                btnAdd.setText("Tambah Mahasiwa");
                btnAdd.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        String name = edtName.getText().toString();
                        String nim = edtNim.getText().toString();
                        if (!name.isEmpty() && !nim.isEmpty()){
                            addNewMahasiswa(name,nim);
                        } else {
                            Toast.makeText(DetailMahasiswaActivity.this,
                                    "maaf nama dan nim tidak boleh kosong.",Toast.LENGTH_LONG).show();
                        }

                    }
                });
                break;
            case Consts.INTENT_EDIT:
                final Mahasiswa mahasiswa = (Mahasiswa) getIntent().getSerializableExtra("mahasiswa");
                edtName.setText(mahasiswa.getName());
                edtNim.setText(mahasiswa.getNim());
                btnAdd.setText("Update Data");
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mahasiswa.setName(edtName.getText().toString());
                        mahasiswa.setNim(edtNim.getText().toString());
                        updateMahasiswa(mahasiswa);
                    }
                });
                break;
        }
    }

    private void updateMahasiswa(Mahasiswa mahasiswa){
        Routes services  = network.request().create(Routes.class);

        String mahasiswaId = String.valueOf(mahasiswa.getId());
        String name = mahasiswa.getName();
        String nim = mahasiswa.getNim();

        services.updateMahasiswa(mahasiswaId,name,nim).enqueue(new Callback<Mahasiswa>() {
            @Override
            public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                if (response.isSuccessful()){
                    Toast.makeText(DetailMahasiswaActivity.this,
                            "update berhasil, dude", Toast.LENGTH_LONG).show();
                    finish();
                } else {

                }
            }

            @Override
            public void onFailure(Call<Mahasiswa> call, Throwable t) {
                onErrorAddMahasiswa();
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
                 onErrorAddMahasiswa();
             }
            }

            @Override
            public void onFailure(Call<Mahasiswa> call, Throwable t) {
                onErrorAddMahasiswa();
            }
        });
    }

    private void onErrorAddMahasiswa() {
        Toast.makeText(DetailMahasiswaActivity.this,
                "Maaf, terjadi kesalahan",
                Toast.LENGTH_LONG).show();
    }
}

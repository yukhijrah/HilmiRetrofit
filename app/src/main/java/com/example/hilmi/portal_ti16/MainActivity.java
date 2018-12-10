package com.example.hilmi.portal_ti16;

import android.content.DialogInterface;
import android.net.Network;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.example.hilmi.portal_ti16.adapter.MahasiswaAdapter;
import com.example.hilmi.portal_ti16.entity.DaftarMahasiswa;
import com.example.hilmi.portal_ti16.entity.Mahasiswa;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menampilkan menu di activity
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                //ketika menu refresh di klik maka panggil ...
                requestDaftarMahasiswa();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestDaftarMahasiswa(){
        final Routes services = network.request().create(Routes.class);

        services.getMahasiswa().enqueue(new Callback<DaftarMahasiswa>() {
            @Override
            public void onResponse(Call<DaftarMahasiswa> call, Response<DaftarMahasiswa> response) {
                //disini melakukan pengecekan request yang di lakukan apakah berhasil / tidak
                if (response.isSuccessful()){
                    //casting data yang didapatkan, menjadi daftar mahasiswa
                    DaftarMahasiswa mahasiswa = response.body();

                    //get tittle
                    Log.d("Hilmi", mahasiswa.getTitle());

                    //tampilkan daftar mahasiswa direcycleview
                    MahasiswaAdapter adapter = new MahasiswaAdapter(mahasiswa.getData());

                    //untuk handle button delete di item mahasiswa
                    //fungsinya untuk menghapus data yang ada di API
                    adapter.setListener(new MahasiswaAdapter.MahasiswaListener() {
                        @Override
                        public void onDelete(int mhsId) {
                            String id = String.valueOf(mhsId); //konversi int to string
                            deleteMahasiswa(services, id);

                        }
                    });
                    listMahasiswa.setAdapter(adapter);
                } else {
                    //ketika data tidak berhasil diload
                    onMahasiswaError();
                }
            }

            @Override
            public void onFailure(Call<DaftarMahasiswa> call, Throwable t) {
                //ketika data tidak berhasil diload
                onMahasiswaError();
            }
        });
    }

    private void onMahasiswaError() {
        Toast.makeText(
                MainActivity.this,
                "Gagal. Silahkan periksa koneksi internet anda.",
                Toast.LENGTH_LONG).show();
    }

    private void deleteMahasiswa(final Routes services, final String mhsId){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.app_name);
        alert.setMessage("are you sure, dude?");
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                services.deleteMahasiswa(mhsId).enqueue(new Callback<Mahasiswa>() {
                    @Override
                    public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                        if (response.isSuccessful()){
                            requestDaftarMahasiswa();
                        } else {
                            onMahasiswaError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Mahasiswa> call, Throwable t) {
                        onMahasiswaError();
                    }
                });
            }
        });
        alert.show();
    }

}

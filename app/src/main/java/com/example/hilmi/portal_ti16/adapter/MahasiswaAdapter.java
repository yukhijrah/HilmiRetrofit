package com.example.hilmi.portal_ti16.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hilmi.portal_ti16.DetailMahasiswaActivity;
import com.example.hilmi.portal_ti16.R;
import com.example.hilmi.portal_ti16.entity.Mahasiswa;
import com.example.hilmi.portal_ti16.holder.MahasiswaHolder;
import com.example.hilmi.portal_ti16.util.Consts;

import java.util.List;

/**
 * Created by hilmi on 26/11/18.
 */

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaHolder>{
    private List<Mahasiswa> mahasiswas;
    private MahasiswaListener listener;

    public MahasiswaAdapter(List<Mahasiswa> mahasiswas) {

        this.mahasiswas = mahasiswas;
    }

    public void setListener(MahasiswaListener listener) {
        this.listener = listener;
    }

    @Override
    public MahasiswaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa, parent, false);
        final MahasiswaHolder Holder = new MahasiswaHolder(itemView);

        final Context context = Holder.itemView.getContext();

        Holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //definisikan position untuk getMahasiswa
                int adapterPosition = Holder.getAdapterPosition();
                Mahasiswa mahasiswa = mahasiswas.get(adapterPosition);

                Intent detailIntent = new Intent(context, DetailMahasiswaActivity.class);
                detailIntent.putExtra("mahasiswa",mahasiswa);
                detailIntent.putExtra(Consts.KEY_ACTION_DETAIL, Consts.INTENT_EDIT);
                context.startActivity(detailIntent);
            }
        });
        return Holder;
    }

    @Override
    public void onBindViewHolder(MahasiswaHolder holder, final int position) {
        holder.txtNama.setText(mahasiswas.get(position).getName());
        holder.txtNim.setText(mahasiswas.get(position).getNim());
        //tambah fungsi delete
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDelete(mahasiswas.get(position).getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return mahasiswas.size();
    }

    public interface MahasiswaListener {
        void onDelete(int mhsId);
    }
}
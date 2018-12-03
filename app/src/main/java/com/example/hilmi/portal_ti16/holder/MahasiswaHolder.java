package com.example.hilmi.portal_ti16.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hilmi.portal_ti16.R;

/**
 * Created by hilmi on 26/11/18.
 */

public class MahasiswaHolder extends RecyclerView.ViewHolder {

    public TextView txtNama;
    public TextView txtNim;

   public MahasiswaHolder (View itemView){
       super(itemView);
       txtNama = (TextView) itemView.findViewById(R.id.txt_nama);
       txtNim = (TextView) itemView.findViewById(R.id.txt_nim);
   }
}

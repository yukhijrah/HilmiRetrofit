package com.example.hilmi.portal_ti16.entity;

import java.util.List;

/**
 * Created by hilmi on 26/11/18.
 */

public class DaftarMahasiswa {
    private String title;
    private List<Mahasiswa> data;

    public String getTitle(){
        return title;
    }
    public List<Mahasiswa> getData(){
        return data;
    }
}

package com.example.hilmi.portal_ti16.entity;

import java.io.Serializable;

/**
 * Created by hilmi on 26/11/18.
 */

public class Mahasiswa implements Serializable {
    private int id;
    private String name;
    private String nim;

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public  String getNim() {
        return nim;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }
}

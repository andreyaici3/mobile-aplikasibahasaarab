package com.mumtaz.aplikasibahasaarab;

import android.provider.BaseColumns;

public class Soal {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String soal;
    private String pil_a;
    private String pil_b;
    private String pil_c;
    private String pil_banar;
    private String bab;

    public String getBab() {
        return bab;
    }

    public void setBab(String bab) {
        this.bab = bab;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getPil_a() {
        return pil_a;
    }

    public void setPil_a(String pil_a) {
        this.pil_a = pil_a;
    }

    public String getPil_b() {
        return pil_b;
    }

    public void setPil_b(String pil_b) {
        this.pil_b = pil_b;
    }

    public String getPil_c() {
        return pil_c;
    }

    public void setPil_c(String pil_c) {
        this.pil_c = pil_c;
    }

    public String getPil_banar() {
        return pil_banar;
    }

    public void setPil_banar(String pil_banar) {
        this.pil_banar = pil_banar;
    }



}

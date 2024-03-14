package com.mumtaz.aplikasibahasaarab.model;

import java.io.Serializable;

public class Nilai implements Serializable {
    private int id;
    private int id_siswa;
    private String nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    private int bab1, bab2, bab3, bab4, bab5, bab6;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_siswa() {
        return id_siswa;
    }

    public void setId_siswa(int id_siswa) {
        this.id_siswa = id_siswa;
    }

    public int getBab1() {
        return bab1;
    }

    public void setBab1(int bab1) {
        this.bab1 = bab1;
    }

    public int getBab2() {
        return bab2;
    }

    public void setBab2(int bab2) {
        this.bab2 = bab2;
    }

    public int getBab3() {
        return bab3;
    }

    public void setBab3(int bab3) {
        this.bab3 = bab3;
    }

    public int getBab4() {
        return bab4;
    }

    public void setBab4(int bab4) {
        this.bab4 = bab4;
    }

    public int getBab5() {
        return bab5;
    }

    public void setBab5(int bab5) {
        this.bab5 = bab5;
    }

    public int getBab6() {
        return bab6;
    }

    public void setBab6(int bab6) {
        this.bab6 = bab6;
    }
}

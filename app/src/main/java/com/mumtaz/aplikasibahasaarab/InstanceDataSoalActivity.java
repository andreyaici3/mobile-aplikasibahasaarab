package com.mumtaz.aplikasibahasaarab;

import java.io.Serializable;

public class InstanceDataSoalActivity implements Serializable {
    private int id_siswa;
    private int bab;
    private int nilai;
    private int role;
    private int waktu_akses;

    public int getId_siswa() {
        return id_siswa;
    }

    public void setId_siswa(int id_siswa) {
        this.id_siswa = id_siswa;
    }

    public int getWaktu_akses() {
        return waktu_akses;
    }

    public void setWaktu_akses(int waktu_akses) {
        this.waktu_akses = waktu_akses;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getBab() {
        return bab;
    }

    public void setBab(int bab) {
        this.bab = bab;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }
}

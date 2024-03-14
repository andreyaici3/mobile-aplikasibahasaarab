package com.mumtaz.aplikasibahasaarab.model;

import java.util.ArrayList;

public class DetailNilai {
    private int idSiswa;
    private String nama;
    private ArrayList<Nil> nilais;

    public int getIdSiswa() {
        return idSiswa;
    }

    public void setIdSiswa(int idSiswa) {
        this.idSiswa = idSiswa;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public ArrayList<Nil> getNilais() {
        return nilais;
    }

    public void setNilais(ArrayList<Nil> nilais) {
        this.nilais = nilais;
    }

    public static class Nil {
        private String bab_id, waktu_akses, nilai;

        public String getBab_id() {
            return bab_id;
        }

        public void setBab_id(String bab_id) {
            this.bab_id = bab_id;
        }

        public String getWaktu_akses() {
            return waktu_akses;
        }

        public void setWaktu_akses(String waktu_akses) {
            this.waktu_akses = waktu_akses;
        }

        public String getNilai() {
            return nilai;
        }

        public void setNilai(String nilai) {
            this.nilai = nilai;
        }
    }
}



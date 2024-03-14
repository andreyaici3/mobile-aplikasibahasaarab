package com.mumtaz.aplikasibahasaarab.guru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.mumtaz.aplikasibahasaarab.DBHelper;
import com.mumtaz.aplikasibahasaarab.Helper;
import com.mumtaz.aplikasibahasaarab.InstanceDataSoalActivity;
import com.mumtaz.aplikasibahasaarab.R;
import com.mumtaz.aplikasibahasaarab.model.Nilai;

public class DetailNilaiSiswa extends Helper {
    private Nilai nilai;
    private TextView nama;
    private Button btnMenu;
    private TextView bab1, bab2, bab3, bab4, bab5, bab6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nilai_siswa);
        initComponent();

    }

    private void initComponent(){
        nilai = (Nilai) getIntent().getSerializableExtra(DBHelper.KeyName.dataPassing);
        nama = (TextView) findViewById(R.id.nama);
        btnMenu = (Button) findViewById(R.id.btnNilaiKembali);
        bab1 = (TextView) findViewById(R.id.bab1);
        bab2 = (TextView) findViewById(R.id.bab2);
        bab3 = (TextView) findViewById(R.id.bab3);
        bab4 = (TextView) findViewById(R.id.bab4);
        bab5 = (TextView) findViewById(R.id.bab5);
        bab6 = (TextView) findViewById(R.id.bab6);

        nama.setText("Nama: " + nilai.getNama());
        bab1.setText("Bab1: " + nilai.getBab1());
        bab2.setText("Bab2: " + nilai.getBab2());
        bab3.setText("Bab3: " + nilai.getBab3());
        bab4.setText("Bab4: " + nilai.getBab4());
        bab5.setText("Bab5: " + nilai.getBab5());
        bab6.setText("Bab6: " + nilai.getBab6());

        btnMenu.setOnClickListener(v -> {
            this.pick();
            this.newIntent(getApplicationContext(), ListNilaiPages.class, true, null);
        });


    }
}
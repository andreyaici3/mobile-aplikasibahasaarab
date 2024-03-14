package com.mumtaz.aplikasibahasaarab.guru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.mumtaz.aplikasibahasaarab.Helper;
import com.mumtaz.aplikasibahasaarab.HomePage;
import com.mumtaz.aplikasibahasaarab.InstanceDataSoalActivity;
import com.mumtaz.aplikasibahasaarab.MateriPagesDhomir;
import com.mumtaz.aplikasibahasaarab.PilihanBabLatihan;
import com.mumtaz.aplikasibahasaarab.R;

public class MenuPageGuru extends Helper {
    Button materi, nilai, kembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page_guru);
        initButton();
        aksiButton();
    }

    private void initButton(){
        materi = (Button) findViewById(R.id.btnHomeBelajar);
        nilai = (Button) findViewById(R.id.btnMenuGuruNilai);
        kembali = (Button) findViewById(R.id.btnHomeKembali);
    }

    private void aksiButton(){
        InstanceDataSoalActivity instanceDataSoalActivity = new InstanceDataSoalActivity();
        instanceDataSoalActivity.setRole(0);
        materi.setOnClickListener(v -> {
            this.pick();
            this.newIntent(getApplicationContext(), MateriPagesDhomir.class, false, instanceDataSoalActivity);
        });
        kembali.setOnClickListener(v -> {
            this.pick();
            this.newIntent(getApplicationContext(), HomePage.class, true, null);
        });
        nilai.setOnClickListener(v -> {
            this.pick();
            this.newIntent(getApplicationContext(), ListNilaiPages.class, false, null);
        });
    }
}
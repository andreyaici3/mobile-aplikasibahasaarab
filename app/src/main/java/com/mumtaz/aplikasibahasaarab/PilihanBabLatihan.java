package com.mumtaz.aplikasibahasaarab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mumtaz.aplikasibahasaarab.model.Level;

public class PilihanBabLatihan extends Helper {
    private Button btnKembali;
    private Button btnBab1, btnBab2, btnBab3, btnBab4, btnBab5, btnBab6;
    private InstanceDataSoalActivity instanceDataSoalActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan_bab_latihan);
        initComponent();
    }

    private void initComponent(){
        Intent myIntent = getIntent();
        Level level = (Level) myIntent.getSerializableExtra(DBHelper.KeyName.dataPassing);

        btnBab1 = (Button) findViewById(R.id.latihanBab1);
        btnBab2 = (Button) findViewById(R.id.latihanBab2);
        btnBab3 = (Button) findViewById(R.id.latihanBab3);
        btnBab4 = (Button) findViewById(R.id.latihanBab4);
        btnBab5 = (Button) findViewById(R.id.latihanBab5);
        btnBab6 = (Button) findViewById(R.id.latihanBab6);
        btnKembali = (Button) findViewById(R.id.btnLatihanSoalKembali);

        btnKembali.setOnClickListener(v ->{
            this.pick();
            this.newIntent(getApplicationContext(), MenuPage.class, true, instanceDataSoalActivity);
        });

        btnBab1.setOnClickListener(v -> cekBab(R.id.latihanBab1));
        if (level.getBab2().equals("lock")){
            btnBab2.setText("BAB 2 TERKUNCI\nKamu Harus Selesaikan Bab 1");
        } else {
            btnBab2.setOnClickListener(v -> cekBab(R.id.latihanBab2));
        }

        if (level.getBab3().equals("lock")){
            btnBab3.setText("BAB 3 TERKUNCI\nKamu Harus Selesaikan Bab 2");
        } else {
            btnBab3.setOnClickListener(v -> cekBab(R.id.latihanBab3));
        }
        if (level.getBab4().equals("lock")){
            btnBab4.setText("BAB 4 TERKUNCI\nKamu Harus Selesaikan Bab 3");
        } else {
            btnBab4.setOnClickListener(v -> cekBab(R.id.latihanBab4));
        }
        if (level.getBab5().equals("lock")){
            btnBab5.setText("BAB 5 TERKUNCI\nKamu Harus Selesaikan Bab 4");
        } else {
            btnBab5.setOnClickListener(v -> cekBab(R.id.latihanBab5));
        }
        if (level.getBab6().equals("lock")){
            btnBab6.setText("BAB 6 TERKUNCI\nKamu Harus Selesaikan Bab 5");
        } else {
            btnBab6.setOnClickListener(v -> cekBab(R.id.latihanBab6));
        }
    }

    private void cekBab(int id){
        instanceDataSoalActivity = new InstanceDataSoalActivity();
        Log.d("DEBUG_ON", "Cek Bab: " + id);
        this.pick();
        switch (id){
            case R.id.latihanBab1:
                instanceDataSoalActivity.setBab(1);
                instanceDataSoalActivity.setNilai(0);
                newIntent(getApplicationContext(), LatihanPages.class, true, instanceDataSoalActivity);
                break;
            case R.id.latihanBab2:
                instanceDataSoalActivity.setBab(2);
                instanceDataSoalActivity.setNilai(0);
                newIntent(getApplicationContext(), LatihanPages.class, true, instanceDataSoalActivity);
                break;
            case R.id.latihanBab3:
                instanceDataSoalActivity.setBab(3);
                instanceDataSoalActivity.setNilai(0);
                newIntent(getApplicationContext(), LatihanPages.class, true, instanceDataSoalActivity);
                break;
            case R.id.latihanBab4:
                instanceDataSoalActivity.setBab(4);
                instanceDataSoalActivity.setNilai(0);
                newIntent(getApplicationContext(), LatihanPages.class, true, instanceDataSoalActivity);
                break;
            case R.id.latihanBab5:
                instanceDataSoalActivity.setBab(5);
                instanceDataSoalActivity.setNilai(0);
                newIntent(getApplicationContext(), LatihanPages.class, true, instanceDataSoalActivity);
                break;
            case R.id.latihanBab6:
                instanceDataSoalActivity.setBab(6);
                instanceDataSoalActivity.setNilai(0);
                newIntent(getApplicationContext(), LatihanPages.class, true, instanceDataSoalActivity);
                break;
            default:
                newIntent(getApplicationContext(), MenuPage.class, true, null);
                break;
        }
    }


}
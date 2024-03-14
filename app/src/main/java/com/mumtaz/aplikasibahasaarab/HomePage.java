package com.mumtaz.aplikasibahasaarab;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.mumtaz.aplikasibahasaarab.guru.LoginGuru;
import com.mumtaz.aplikasibahasaarab.model.Siswa;

public class HomePage extends Helper {

    private Button btnSiswa;
    private Button btnGuru, tentang;
    private MediaPlayer sound;
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        sound = MediaPlayer.create(this, R.raw.home);
        sound.start();
        initComponent();

        mPrefs =  getSharedPreferences(Config.KEY_NAME_SHARED, MODE_PRIVATE);

        Gson gson = new Gson();
        String json = mPrefs.getString(Config.KEY_STORE_DATA, null);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        if (json != null){
            Siswa obj = gson.fromJson(json, Siswa.class);
            btnSiswa.setOnClickListener(v -> {
                sound.stop();
                this.pick();
                this.newIntent(getApplicationContext(), MenuPage.class, true, null);
            });
        } else {
            btnSiswa.setOnClickListener(v -> {
                sound.stop();
                this.pick();
                this.newIntent(getApplicationContext(), LoginPageSiswa.class, false, null);
            });
        }
    }

    private void initComponent(){
        btnSiswa = (Button) findViewById(R.id.btnLoginSiswa);
        btnGuru = (Button) findViewById(R.id.btnLoginGuru);
        tentang = (Button) findViewById(R.id.btnHomeTentang);

        aksi();
    }

    private void aksi(){
        tentang.setOnClickListener(v -> {
            sound.stop();
            this.pick();
            this.newIntent(getApplicationContext(), AboutPage.class, false, null);
        });


        btnGuru.setOnClickListener(v -> {
            sound.stop();
            this.pick();
            this.newIntent(getApplicationContext(), LoginGuru.class, false, null);
        });
    }



}
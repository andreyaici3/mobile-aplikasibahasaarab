package com.mumtaz.aplikasibahasaarab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutPage extends Helper {

    Button btnkembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
        initComponent();
        initAksi();
    }

    private void initComponent(){
        btnkembali = (Button) findViewById(R.id.btnAboutKembali);
    }

    private void initAksi(){
        btnkembali.setOnClickListener(v -> {
            this.newIntent(getApplicationContext(), HomePage.class, true, null);
        });
    }
}
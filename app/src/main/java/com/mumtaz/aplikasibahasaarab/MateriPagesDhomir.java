package com.mumtaz.aplikasibahasaarab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mumtaz.aplikasibahasaarab.guru.MenuPageGuru;


public class MateriPagesDhomir extends Helper {

    Toolbar toolbar;
    LinearLayout wrapperMateri;
    Button btnKembali;
    ImageView bab1PlayBtn;
    MediaPlayer mediaPlayer, mediaPlayer1;
    InstanceDataSoalActivity instanceDataSoalActivity;
    View bab1View;
    boolean play = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_pages_dhomir);
        instanceDataSoalActivity = (InstanceDataSoalActivity) getIntent().getSerializableExtra(DBHelper.KeyName.dataPassing);


        wrapperMateri = (LinearLayout) findViewById(R.id.wrapperMateri);
        mediaPlayer = MediaPlayer.create(this, R.raw.bab1);
        mediaPlayer.start();
        bab1PlayBtn = (ImageView) findViewById(R.id.playDhomir);
        bab1PlayBtn.setClickable(true);

        mediaPlayer1 = MediaPlayer.create(this, R.raw.bab1dhomir);
        bab1PlayBtn.setOnClickListener(v -> {
            mediaPlayer.stop();
            if (!play){
                mediaPlayer1.start();
                play = true;
            } else {
                mediaPlayer1.pause();
                play = false;
            }
        });


        toolbar = findViewById(R.id.mainToolbar);
        btnKembali = (Button) findViewById(R.id.btnMateriKembali);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDefaultPopupMenu(v);
            }
        });

        if (instanceDataSoalActivity.getRole() == 1){
            //siswa
            btnKembali.setOnClickListener(v -> {
                this.pick();
                this.newIntent(getApplicationContext(), MenuPage.class, true,null);
            });
        } else {
            btnKembali.setOnClickListener(v -> {
                this.pick();
                this.newIntent(getApplicationContext(), MenuPageGuru.class, true,null);
            });
        }
    }

    public void showDefaultPopupMenu(View view) {
        this.pick();
        showPopupMenu(view, false, R.style.MyPopupStyle);
    }

    private void showPopupMenu(View anchor, boolean isWithIcons, int style){
        Context wrapper = new ContextThemeWrapper(this, style);
        PopupMenu popup = new PopupMenu(wrapper, anchor);
        popup.getMenuInflater().inflate(R.menu.nav_materi, popup.getMenu());


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                MateriPagesDhomir.this.pick();
                View C = findViewById(R.id.wrapperMateri);
                ViewGroup parent = (ViewGroup) C.getParent();
                int index = parent.indexOfChild(C);
                parent.removeView(C);
                switch (item.getItemId()){

                    case R.id.dhomir:
                        play = false;
                        mediaPlayer1.stop();
                        mediaPlayer.stop();
                        mediaPlayer = MediaPlayer.create(MateriPagesDhomir.this, R.raw.bab1);
                        mediaPlayer.start();
                        mediaPlayer1 = MediaPlayer.create(MateriPagesDhomir.this, R.raw.bab1dhomir);
                        C = getLayoutInflater().inflate(R.layout.part_dhomir, parent, false);
                        parent.addView(C, index);
                        Button bd = C.findViewById(R.id.playDhomir);
                        bd.setOnClickListener(v -> {
                            mediaPlayer.stop();
                            if (!play){
                                mediaPlayer1.start();
                                play = true;
                            } else {
                                mediaPlayer1.pause();
                                play = false;
                            }
                        });

                        break;
                    case R.id.maqsur:
                        C = getLayoutInflater().inflate(R.layout.part_maqsur, parent, false);
                        parent.addView(C, index);
                        play = false;
                        mediaPlayer.stop();
                        mediaPlayer1.stop();
                        mediaPlayer = MediaPlayer.create(MateriPagesDhomir.this, R.raw.bab2);
                        mediaPlayer.start();
                        ImageView bm = (ImageView) C.findViewById(R.id.playMaqsur);
                        mediaPlayer1 = MediaPlayer.create(MateriPagesDhomir.this, R.raw.bab2maqsur);
                        bm.setClickable(true);
                        bm.setOnClickListener(v -> {
                            mediaPlayer.stop();
                            if (!play){
                                mediaPlayer1.start();
                                play = true;
                            } else {
                                mediaPlayer1.pause();
                                play = false;
                            }
                        });
                        break;
                    case R.id.manqus:
                        C = getLayoutInflater().inflate(R.layout.part_manqus, parent, false);
                        parent.addView(C, index);
                        play = false;
                        mediaPlayer1.stop();
                        mediaPlayer.stop();
                        mediaPlayer = MediaPlayer.create(MateriPagesDhomir.this, R.raw.bab3);
                        mediaPlayer.start();
                        mediaPlayer1 = MediaPlayer.create(MateriPagesDhomir.this, R.raw.bab3manqus);
                        ImageView bmq = (ImageView) C.findViewById(R.id.playManqus);
                        bmq.setClickable(true);
                        bmq.setOnClickListener(v -> {
                            mediaPlayer.stop();
                            if (!play){
                                mediaPlayer1.start();
                                play = true;
                            } else {
                                mediaPlayer1.pause();
                                play = false;
                            }
                        });
                        break;
                    case R.id.mutsanna:
                        C = getLayoutInflater().inflate(R.layout.part_mutsanna, parent, false);
                        parent.addView(C, index);
                        play = false;
                        mediaPlayer1.stop();
                        mediaPlayer.stop();
                        mediaPlayer = MediaPlayer.create(MateriPagesDhomir.this, R.raw.bab4);
                        mediaPlayer.start();
                        mediaPlayer1 = MediaPlayer.create(MateriPagesDhomir.this, R.raw.bab4mutsana);
                        ImageView bmu = (ImageView) C.findViewById(R.id.playMutsanna);
                        bmu.setClickable(true);
                        bmu.setOnClickListener(v -> {
                            mediaPlayer.stop();
                            if (!play){
                                mediaPlayer1.start();
                                play = true;
                            } else {
                                mediaPlayer1.pause();
                                play = false;
                            }
                        });
                        break;
                    case R.id.mudzakar:
                        C = getLayoutInflater().inflate(R.layout.part_mudzakar, parent, false);
                        parent.addView(C, index);
                        play = false;
                        mediaPlayer1.stop();
                        mediaPlayer.stop();
                        mediaPlayer = MediaPlayer.create(MateriPagesDhomir.this, R.raw.bab5);
                        mediaPlayer.start();
                        mediaPlayer1 = MediaPlayer.create(MateriPagesDhomir.this, R.raw.bab5mudzakar);
                        ImageView bmud = (ImageView) C.findViewById(R.id.playMudzakar);
                        bmud.setClickable(true);
                        bmud.setOnClickListener(v -> {
                            mediaPlayer.stop();
                            if (!play){
                                mediaPlayer1.start();
                                play = true;
                            } else {
                                mediaPlayer1.pause();
                                play = false;
                            }
                        });
                        break;
                    case R.id.mamnu:
                        C = getLayoutInflater().inflate(R.layout.part_mamnu, parent, false);
                        parent.addView(C, index);
                        play = false;
                        mediaPlayer1.stop();
                        mediaPlayer.stop();
                        mediaPlayer = MediaPlayer.create(MateriPagesDhomir.this, R.raw.bab6);
                        mediaPlayer.start();
                        mediaPlayer1 = MediaPlayer.create(MateriPagesDhomir.this, R.raw.bab6mamnu);
                        ImageView bmut = (ImageView) C.findViewById(R.id.playMamnu);
                        bmut.setClickable(true);
                        bmut.setOnClickListener(v -> {
                            mediaPlayer.stop();
                            if (!play){
                                mediaPlayer1.start();
                                play = true;
                            } else {
                                mediaPlayer1.pause();
                                play = false;
                            }
                        });

                        break;
                    default:
                        break;
                }


                return true;
            }
        });

        popup.show();
    }


}

package com.mumtaz.aplikasibahasaarab;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class Helper extends AppCompatActivity {
    private MediaPlayer pick;

    public void pick(){
        pick = MediaPlayer.create(this, R.raw.pick);
        pick.start();
    }


    public void newIntent(Context context, Class clas, boolean dropPageBefore, Serializable extra){
        Intent i = new Intent(context, clas);

        if (extra != null){
            i.putExtra(DBHelper.KeyName.dataPassing, extra);
        }

        if (dropPageBefore){
            finish();
        }
        startActivity(i);
    }

}

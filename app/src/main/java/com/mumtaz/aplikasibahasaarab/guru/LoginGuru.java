package com.mumtaz.aplikasibahasaarab.guru;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mumtaz.aplikasibahasaarab.Helper;
import com.mumtaz.aplikasibahasaarab.HomePage;
import com.mumtaz.aplikasibahasaarab.*;

public class LoginGuru extends Helper {
    private Button btnLogin;
    private Button btnKembali;
    private TextView edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_guru);
        initComponent();
    }


    private void initComponent(){
        btnLogin = (Button) findViewById(R.id.btnLoginGuru1);
        btnKembali = (Button) findViewById(R.id.btnLoginGuruKembali);
        edtPassword = (TextView) findViewById(R.id.edtPassword);
        btnLogin.setOnClickListener(v -> {
            this.pick();
            if (edtPassword.getText().toString().equals("12345")){
                Toast.makeText(getApplicationContext(), "Password Benar!!", Toast.LENGTH_LONG).show();
                this.newIntent(getApplicationContext(), MenuPageGuru.class, true, null);
            } else {
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(
                        LoginGuru.this);

                alertDialogBuilder
                        .setMessage("Silahkan Periska Kembali Password!")
                        .setNegativeButton("Oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogs, int which) {
                                dialogs.dismiss();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        btnKembali.setOnClickListener(v -> {
            this.pick();
            this.newIntent(getApplicationContext(), HomePage.class, true, null);
        });
    }
}
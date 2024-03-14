package com.mumtaz.aplikasibahasaarab;

import androidx.annotation.Nullable;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mumtaz.aplikasibahasaarab.model.Siswa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPageSiswa extends Helper {
    private RequestQueue requestQueue;
    private ProgressDialog dialog;
    private Button btnKembali;
    private Button btnLogin;
    private EditText edtUsername, edtPassword;
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_siswa);
        initComponent();
        mPrefs =  getSharedPreferences(Config.KEY_NAME_SHARED, MODE_PRIVATE);

        Gson gson = new Gson();
        String json = mPrefs.getString(Config.KEY_STORE_DATA, null);
        if (json != null){
            Siswa obj = gson.fromJson(json, Siswa.class);
        }

    }

    void initComponent(){
        dialog = new ProgressDialog(LoginPageSiswa.this);
        requestQueue = Volley.newRequestQueue(LoginPageSiswa.this);
        btnKembali = (Button) findViewById(R.id.btnLoginKembali);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword) ;
        btnLogin.setOnClickListener(v -> {
            this.pick();
            AsyncTaskApi login = new AsyncTaskApi();
            login.execute();

        });
        btnKembali.setOnClickListener(v -> {
            this.pick();
            this.newIntent(getApplicationContext(), HomePage.class, true, null);
        });
    }

    private class AsyncTaskApi extends AsyncTask<Void, Void, String>{
        private String jsonResult;

        @Override
        protected String doInBackground(Void... voids) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_LOGIN, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                   jsonResult = response;
//                    Toast.makeText(LoginPageSiswa.this, "Username / Password Salah", Toast.LENGTH_LONG).show();
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        JSONObject data = jsonObject.getJSONObject("data");
//

                        Siswa siswa = new Siswa();
                        siswa.setId(data.getInt("id"));
                        siswa.setNama(data.getString("nama"));
                        siswa.setUsername(data.getString("username"));

                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(siswa);
                        prefsEditor.putString(Config.KEY_STORE_DATA, json);
                        prefsEditor.commit();

                        LoginPageSiswa.this.newIntent(getApplicationContext(), MenuPage.class, true, null);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginPageSiswa.this, "Username / Password Salah", Toast.LENGTH_LONG).show();

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(Config.KEY_USERNAME, edtUsername.getText().toString());
                    params.put(Config.KEY_PASSWORD, edtPassword.getText().toString());
                    return params;
                }
            };
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            requestQueue.add(stringRequest);
            return "Selesai";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Mohon Tunggu Sebentar");
            dialog.show();
        }



        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
        }


    }



}
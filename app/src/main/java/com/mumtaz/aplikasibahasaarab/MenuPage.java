package com.mumtaz.aplikasibahasaarab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mumtaz.aplikasibahasaarab.model.Level;
import com.mumtaz.aplikasibahasaarab.model.Siswa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Files;

public class MenuPage extends Helper {
    private RequestQueue requestQueue;
    private ProgressDialog dialog;
    Button materi, latihan, kembali;
    SharedPreferences mPrefs;
    Siswa obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
        initButton();
        aksiButton();

        mPrefs =  getSharedPreferences(Config.KEY_NAME_SHARED, MODE_PRIVATE);

        Gson gson = new Gson();
        String json = mPrefs.getString(Config.KEY_STORE_DATA, null);

        if (json != null){
            obj = gson.fromJson(json, Siswa.class);
        }



    }

    private void initButton(){
        materi = (Button) findViewById(R.id.btnHomeBelajar);
        latihan = (Button) findViewById(R.id.btnHomeLatihan);
        kembali = (Button) findViewById(R.id.btnHomeKembali);
        dialog = new ProgressDialog(MenuPage.this);
        requestQueue = Volley.newRequestQueue(MenuPage.this);
    }

    private void aksiButton(){
        InstanceDataSoalActivity instanceDataSoalActivity = new InstanceDataSoalActivity();
        instanceDataSoalActivity.setRole(1);
        latihan.setOnClickListener(v -> {
            this.pick();
            initApi();
        });
        materi.setOnClickListener(v -> {
            this.pick();
            this.newIntent(getApplicationContext(), MateriPagesDhomir.class, false, instanceDataSoalActivity);
        });
        kembali.setOnClickListener(v -> {
            this.pick();
            mPrefs.edit().clear().commit();
            mPrefs.edit().apply();
            this.newIntent(getApplicationContext(), HomePage.class, true, null);
        });


    }

    private void initApi(){
        MenuPage.AsyncTaskApi task = new MenuPage.AsyncTaskApi();
        task.execute();
    }

    private class AsyncTaskApi extends AsyncTask<Void, Void, String>{
        private String jsonResult;

        @Override
        protected String doInBackground(Void... voids) {
            String url = Config.URL_GET_LEVEL + obj.getId();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.URL_GET_LEVEL + obj.getId(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject jo = jsonObject.getJSONObject("data");
                        Level level = new Level();
                        level.setBab1(jo.getString("bab1"));
                        level.setBab2(jo.getString("bab2"));
                        level.setBab3(jo.getString("bab3"));
                        level.setBab4(jo.getString("bab4"));
                        level.setBab5(jo.getString("bab5"));
                        level.setBab6(jo.getString("bab6"));

                        MenuPage.this.newIntent(getApplicationContext(), PilihanBabLatihan.class, false, level);
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(stringRequest);
            return "OK";
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
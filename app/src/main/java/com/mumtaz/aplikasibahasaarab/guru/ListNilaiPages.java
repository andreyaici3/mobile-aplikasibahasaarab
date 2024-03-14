package com.mumtaz.aplikasibahasaarab.guru;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.mumtaz.aplikasibahasaarab.Config;
import com.mumtaz.aplikasibahasaarab.HasilLatihan;
import com.mumtaz.aplikasibahasaarab.Helper;
import com.mumtaz.aplikasibahasaarab.R;
import com.mumtaz.aplikasibahasaarab.model.DetailNilai;
import com.mumtaz.aplikasibahasaarab.model.Nilai;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListNilaiPages extends Helper {
    private Button kembali;
    private ArrayList<Nilai> nilais;
    private ProgressDialog dialog;
    private RequestQueue requestQueue;
    private ListView listView;
    private ArrayList<DetailNilai> nilai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_grade_page);
        initComponent();
    }

    private void initComponent(){
        listView = (ListView) findViewById(R.id.listView1);
        nilais = new ArrayList<>();
        dialog = new ProgressDialog(ListNilaiPages.this);
        requestQueue = Volley.newRequestQueue(ListNilaiPages.this);
        kembali = (Button) findViewById(R.id.btnNilaiKembali);
        kembali.setOnClickListener(v -> {
            this.pick();
            this.newIntent(getApplicationContext(), MenuPageGuru.class, true, null);
        });
        nilai = new ArrayList<>();
        AsyncTaskApi asyncTaskApi = new AsyncTaskApi();
        asyncTaskApi.execute();
    }

    private class AsyncTaskApi extends AsyncTask<Void, Void, String> {
        private String jsonResult;

        @Override
        protected String doInBackground(Void... voids) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.URL_GET_NILAI, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    jsonResult = response;
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray dataSiswa = jsonObject.getJSONArray("data");
                        for (int i = 0; i<dataSiswa.length(); i++){
                            DetailNilai detailNilai = new DetailNilai();
                            detailNilai.setNama(dataSiswa.getJSONObject(i).getString("nama"));
                            detailNilai.setIdSiswa(dataSiswa.getJSONObject(i).getInt("id"));
                            Log.d("DEBUG_ON",dataSiswa.getJSONObject(i).getJSONArray("nilai").length() + "");

//                            ArrayList<DetailNilai.Nil> dnArray = new ArrayList<>();
//                            for (int j=0; j<jo.length(); j++){
//                                DetailNilai.Nil dn = new DetailNilai.Nil();
//                                dn.setNilai(jo.getJSONObject(i).getString("nilai"));;
//                                dn.setBab_id(jo.getJSONObject(i).getString("bab_id"));
//                                dnArray.add(dn);
//
//                            }
//                            detailNilai.setNilais(dnArray);
//                            nilai.add(detailNilai);
                        }

//                        siswa 1 nilai




//                        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("data");

//                        if (jsonArray.length() > 0){
//                            for (int i=0; i<jsonArray.length(); i++){
//                                Nilai nilai = new Nilai();
//                                nilai.setNama(jsonArray.getJSONObject(i).getString("nama"));
//                                nilai.setBab1(jsonArray.getJSONObject(i).getInt("bab1"));
//                                nilai.setBab2(jsonArray.getJSONObject(i).getInt("bab2"));
//                                nilai.setBab3(jsonArray.getJSONObject(i).getInt("bab3"));
//                                nilai.setBab4(jsonArray.getJSONObject(i).getInt("bab4"));
//                                nilai.setBab5(jsonArray.getJSONObject(i).getInt("bab5"));
//                                nilai.setBab6(jsonArray.getJSONObject(i).getInt("bab6"));
//                                nilais.add(nilai);
//                            }
//
//                            AdapterNilai adapterNilai = new AdapterNilai(ListNilaiPages.this, nilais);
//                            listView.setAdapter(adapterNilai);
//                            listView.setOnItemClickListener((parent, view, position, id) -> {
//                                ListNilaiPages.this.pick();
//                                ListNilaiPages.this.newIntent(getApplicationContext(), DetailNilaiSiswa.class, false, nilais.get(position));
//                            });
//                        }

                    } catch (JSONException e){
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            });
            try {
                Thread.sleep(3000);
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

package com.mumtaz.aplikasibahasaarab;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mumtaz.aplikasibahasaarab.model.Nilai;
import com.mumtaz.aplikasibahasaarab.model.Siswa;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class HasilLatihan extends Helper {
    private TextView nama, nilai, waktu;
    private Button btnMenu;
    SharedPreferences mPrefs;
    private InstanceDataSoalActivity instanceDataSoalActivity;
    private RequestQueue requestQueue;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_latihan);

        instanceDataSoalActivity = (InstanceDataSoalActivity) getIntent().getSerializableExtra(DBHelper.KeyName.dataPassing);

        dialog = new ProgressDialog(HasilLatihan.this);
        requestQueue = Volley.newRequestQueue(HasilLatihan.this);
        btnMenu = (Button) findViewById(R.id.btnNilaiKembali);
        nama = (TextView) findViewById(R.id.nama);
        nilai = (TextView) findViewById(R.id.nilai);
        waktu = (TextView) findViewById(R.id.waktu);
        mPrefs = getSharedPreferences(Config.KEY_NAME_SHARED, MODE_PRIVATE);

        Gson gson = new Gson();
        String json = mPrefs.getString(Config.KEY_STORE_DATA, null);
        if (json != null){
            Siswa obj = gson.fromJson(json, Siswa.class);
            nama.setText("Nama: " + obj.getNama());
            nilai.setText("Nilai: " + instanceDataSoalActivity.getNilai());

            Date d = new Date(instanceDataSoalActivity.getWaktu_akses() * 1000L);
            SimpleDateFormat df = new SimpleDateFormat("mm:ss"); // HH for 0-23
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
            String time = df.format(d);
            waktu.setText("Waktu Terpakai: " + time);
            initComponent();
            AsyncTaskApi asyncTaskApi = new AsyncTaskApi(String.valueOf(instanceDataSoalActivity.getBab()), String.valueOf(instanceDataSoalActivity.getNilai()), String.valueOf(obj.getId()), time);
            asyncTaskApi.execute();
        }

    }

    private void initComponent(){
        btnMenu.setOnClickListener(v->{
            this.newIntent(getApplicationContext(), MenuPage.class, true, null);
        });
    }

    private class AsyncTaskApi extends AsyncTask<Void, Void, String> {
        private String jsonResult;
        private String id, nilai, bab, waktu;
        public AsyncTaskApi(String bab, String nilai, String id, String waktu){
            this.id = id;
            this.bab = bab;
            this.nilai = nilai;
            this.waktu = waktu;
        }

        @Override
        protected String doInBackground(Void... voids) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_STORE, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    jsonResult = response;
                    Toast.makeText(getApplicationContext(), "Nilai Berhasil Di Simpan", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(Config.KEY_ID, id);
                    params.put(Config.KEY_BAB, bab);
                    params.put(Config.KEY_NILAI, nilai);
                    params.put(Config.KEY_WAKTI, waktu);
                    return params;
                }
            };
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
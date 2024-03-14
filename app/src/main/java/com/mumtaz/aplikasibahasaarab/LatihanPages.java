package com.mumtaz.aplikasibahasaarab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Debug;
import android.provider.BaseColumns;
import android.text.Html;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mumtaz.aplikasibahasaarab.model.Siswa;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class LatihanPages extends Helper {

    private ArrayList<Soal> soals;
    private DBSoalHelper dbSoalHelper;
    private int bab;
    private int nomorSoal = 0;
    private int indexSoal = 0;
    private TextView tvNomorSoal;
    private TextView tvSoal;
    private int jumlahBenar = 0;
    private Button btnA;
    private Button btnB;
    private Button btnC;
    private TextView tvCounter, judulWaktu;
    private ArrayList<Integer> resultLCM;
    private InstanceDataSoalActivity instanceDataSoalActivity;
    public  CountDownTimer count;
    private LinearLayout pg;
    private MediaPlayer sound;
    private MediaPlayer bgSound;
    private MediaPlayer pick;
    private MediaPlayer salah, benar;
    private int timeUsage;
    SharedPreferences mPrefs;
    Siswa obj;

    public LatihanPages(){
        dbSoalHelper = new DBSoalHelper(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latihan_pages);

        mPrefs =  getSharedPreferences(Config.KEY_NAME_SHARED, MODE_PRIVATE);

        Gson gson = new Gson();
        String json = mPrefs.getString(Config.KEY_STORE_DATA, null);

        if (json != null){
            obj = gson.fromJson(json, Siswa.class);
        }

      instanceDataSoalActivity = (InstanceDataSoalActivity) getIntent().getSerializableExtra(DBHelper.KeyName.dataPassing);
        bab = instanceDataSoalActivity.getBab();

        switch (bab){
            case 1:
                sound = MediaPlayer.create(LatihanPages.this, R.raw.latihanbab1);
                sound.start();
                break;
            case 2:
                sound = MediaPlayer.create(LatihanPages.this, R.raw.latihanbab2);
                sound.start();
                break;
            case 3:
                sound = MediaPlayer.create(LatihanPages.this, R.raw.latihanbab3);
                sound.start();
                break;
            case 4:
                sound = MediaPlayer.create(LatihanPages.this, R.raw.latihanbab4);
                sound.start();
                break;
            case 5:
                sound = MediaPlayer.create(LatihanPages.this, R.raw.latihanbab5);
                sound.start();
                break;
            case 6:
                sound = MediaPlayer.create(LatihanPages.this, R.raw.latihanbab6);
                sound.start();
                break;
            default:
                bab = 0;
                break;
        }


        initComponent();


        initSoal();




        count = new CountDownTimer(300000, 1000){
            public void onTick(long millisUntilFinished) {
                NumberFormat f = new DecimalFormat("00");
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                tvCounter.setText( f.format(min) + ":" + f.format(sec));

                int second = (int) ((300000 - millisUntilFinished) / 1000);
                timeUsage = second;
            }

            public void onFinish() {
                judulWaktu.setText("Jumlah Jawaban Benar");
                tvCounter.setText(jumlahBenar + "");
                pg.removeAllViewsInLayout();
                timeUsage = timeUsage +1;
                Log.d("DEBUG_ON", "waktu terpakai: " + timeUsage);
                try {
                    Thread.sleep(1000);
                    instanceDataSoalActivity.setNilai(jumlahBenar);
                    instanceDataSoalActivity.setWaktu_akses(timeUsage);
                    instanceDataSoalActivity.setId_siswa(obj.getId());
                    newIntent(getApplicationContext(), HasilLatihan.class, true, instanceDataSoalActivity);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }.start();




    }

    private void initComponent(){
        timeUsage = 0;
        tvNomorSoal = (TextView) findViewById(R.id.nomorSoal);
        tvSoal = (TextView) findViewById(R.id.soalText);
        tvCounter = (TextView) findViewById(R.id.counter);
        judulWaktu = (TextView) findViewById(R.id.judulWaktu);
        pg = (LinearLayout) findViewById(R.id.wrapperSoal);

        btnA = (Button) findViewById(R.id.pil_a);
        btnB = (Button) findViewById(R.id.pil_b);
        btnC = (Button) findViewById(R.id.pil_c);

        btnA.setOnClickListener(v -> cekJawaban(btnA.getId()));
        btnB.setOnClickListener(v -> cekJawaban(btnB.getId()));
        btnC.setOnClickListener(v -> cekJawaban(btnC.getId()));

        bgSound = MediaPlayer.create(this, R.raw.bglatihan);
        pick = MediaPlayer.create(this, R.raw.pick);
        benar = MediaPlayer.create(this, R.raw.benar3);
        salah = MediaPlayer.create(this, R.raw.salahsfx);



    }

    private void initSoal(){
        dbSoalHelper.open();
        Cursor cursor = dbSoalHelper.fetch(String.valueOf(bab));
        soals = new ArrayList<>();
        resultLCM = new ArrayList<>();
        while (cursor.moveToNext()){
            Soal soal = new Soal();
            soal.setId(cursor.getInt(0));
            soal.setSoal(cursor.getString(1));
            soal.setPil_a(cursor.getString(2));
            soal.setPil_b(cursor.getString(3));
            soal.setPil_c(cursor.getString(4));
            soal.setPil_banar(cursor.getString(5));
            soal.setBab(cursor.getString(6));
            soals.add(soal);
        }
        dbSoalHelper.close();
        resultLCM = LCM(soals.size());
        indexSoal = resultLCM.get(nomorSoal);

         masukSoal(indexSoal);
    }

    private void masukSoal(int index){
        tvNomorSoal.setText(nomorSoal + 1 +"");
        tvSoal.setText(Html.fromHtml(soals.get(index).getSoal()));
        btnA.setText(soals.get(index).getPil_a());
        btnB.setText(soals.get(index).getPil_b());
        btnC.setText(soals.get(index).getPil_c());
    }

    private void cekJawaban(int id){
        pick.start();
        String kunciJawaban = soals.get(indexSoal).getPil_banar();
        String strKunciJawaban = "";
        switch (kunciJawaban){
            case "a":
                strKunciJawaban = soals.get(indexSoal).getPil_a();
                break;
            case "b":
                strKunciJawaban = soals.get(indexSoal).getPil_b();
                break;
            case "c":
                strKunciJawaban = soals.get(indexSoal).getPil_c();
                break;
            default:
                strKunciJawaban = "Tidak Ada Jawaban";
                break;
        }

        String jawabanSementara = null;
        switch (id){
            case R.id.pil_a:
                jawabanSementara = "a";

                break;
            case R.id.pil_b:
                jawabanSementara = "b";
                break;
            case R.id.pil_c:
                jawabanSementara = "c";
                break;
            default:
                break;
        }

        if (jawabanSementara.equals(kunciJawaban)){
            jumlahBenar++;
            showAlert(true, strKunciJawaban);

        }else {
            showAlert(false, strKunciJawaban);

        }

        if (nomorSoal < 9){
            nomorSoal++;
            indexSoal = resultLCM.get(nomorSoal);
            masukSoal(indexSoal);
        } else {
            count.cancel();
            pg.removeAllViewsInLayout();
            judulWaktu.setText("Jumlah Jawaban Benar");
            tvCounter.setText(jumlahBenar + "");
            Log.d("DEBUG_ON", "waktu terpakai: " + timeUsage);
            try {
                Thread.sleep(1000);
                timeUsage = timeUsage +1;
                instanceDataSoalActivity.setNilai(jumlahBenar);
                instanceDataSoalActivity.setWaktu_akses(timeUsage);
                instanceDataSoalActivity.setId_siswa(obj.getId());
                this.newIntent(getApplicationContext(), HasilLatihan.class, true, instanceDataSoalActivity);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }


    }

    private void showAlert(boolean jawaban, String strKunciJawaban){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View customLayout = getLayoutInflater().inflate(R.layout.alert_dialog_custom, null);
        Button btnLanjutkan = customLayout.findViewById(R.id.btnLanjutkan);
        ImageView icNotif = customLayout.findViewById(R.id.icNotif);
        TextView textNotif = customLayout.findViewById(R.id.textNotif);
        TextView hasilJawaban = customLayout.findViewById(R.id.hasilJawaban);
        TextView jawabanBenar = customLayout.findViewById(R.id.jawabanBenar);

        if (jawaban){
            btnLanjutkan.setBackgroundColor(getResources().getColor(R.color.green));
            btnLanjutkan.setText("LANJUTKAN");
            icNotif.setImageResource(R.drawable.success);
            textNotif.setText("BENAR!!!");
            textNotif.setTextColor(getResources().getColor(R.color.green));
            hasilJawaban.setTextColor(getResources().getColor(R.color.green));
            jawabanBenar.setTextColor(getResources().getColor(R.color.green));
            benar.start();
        } else {
            btnLanjutkan.setBackgroundColor(getResources().getColor(R.color.red));
            btnLanjutkan.setText("OKE!");
            textNotif.setText("SALAH!!!");
            salah.start();
        }

        jawabanBenar.setText(strKunciJawaban);
        builder.setView(customLayout);
        builder.setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();

        btnLanjutkan.setOnClickListener(v1 -> {
            dialog.dismiss();
        });
    }

    private ArrayList<Integer> LCM(int length){

        ArrayList<Integer> bilanganAcak = new ArrayList<Integer>();

        //Inisasi
        int a = 10;
        int c = 23;
        int m = length;

        Random random = new Random();
        int xn = random.nextInt(m);

        for (int i=0; i<10; i++){
            xn = ((a * xn) + c) % m;
            bilanganAcak.add(xn);
        }
        return bilanganAcak;
    }

}
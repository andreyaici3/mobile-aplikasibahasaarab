package com.mumtaz.aplikasibahasaarab;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBHelper extends SQLiteAssetHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "soal.db";
    private SQLiteDatabase database;

    public  DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static class TableInformationSoal {
        public static final String TABLE_NAME = "tb_soal";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_SOAL = "soal";
        public static final String COLUMN_NAME_PIL_A = "pil_a";
        public static final String COLUMN_NAME_PIL_B = "pil_b";
        public static final String COLUMN_NAME_PIL_C = "pil_c";
        public static final String COLUMN_NAME_PIL_BENAR = "pil_benar";
        public static final String COLUMN_NAME_BAB = "bab";
    }

    public static class KeyName {
        public static String dataPassing = "data";
    }
}

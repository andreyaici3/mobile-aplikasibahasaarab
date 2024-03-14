package com.mumtaz.aplikasibahasaarab;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Debug;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.Serializable;

public class DBSoalHelper {
    private DBHelper dbHelper;
    private Context context;

    private SQLiteDatabase database;

    public DBSoalHelper(Context c){
        this.context = c;
    }

    public DBSoalHelper open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public Cursor fetch(String bab) {
        String[] columns = {
            DBHelper.TableInformationSoal.COLUMN_NAME_ID,
            DBHelper.TableInformationSoal.COLUMN_NAME_SOAL,
            DBHelper.TableInformationSoal.COLUMN_NAME_PIL_A,
            DBHelper.TableInformationSoal.COLUMN_NAME_PIL_B,
            DBHelper.TableInformationSoal.COLUMN_NAME_PIL_C,
            DBHelper.TableInformationSoal.COLUMN_NAME_PIL_BENAR,
            DBHelper.TableInformationSoal.COLUMN_NAME_BAB
        };
        Cursor cursor = database.query(DBHelper.TableInformationSoal.TABLE_NAME, columns, DBHelper.TableInformationSoal.COLUMN_NAME_BAB + "=?", new String[]{bab}, null, null, null);

        if (cursor == null) {
            cursor.moveToFirst();
        }
        return cursor;
    }






}

package com.mumtaz.aplikasibahasaarab.guru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mumtaz.aplikasibahasaarab.R;
import com.mumtaz.aplikasibahasaarab.model.Nilai;

import java.util.ArrayList;
import java.util.List;

public class AdapterNilai extends ArrayAdapter<Nilai> {
    private ArrayList<Nilai> dataSet;
    Context mContext;

    public AdapterNilai(Context context, ArrayList<Nilai> data) {
        super(context, 0, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_list_nilai, parent, false);
        TextView nomor = (TextView) view.findViewById(R.id.nomor);
        TextView nama = (TextView) view.findViewById(R.id.namaPeserta);
        nama.setText(dataSet.get(position).getNama());
        nomor.setText("" + (position + 1));
        return  view;
    }
}

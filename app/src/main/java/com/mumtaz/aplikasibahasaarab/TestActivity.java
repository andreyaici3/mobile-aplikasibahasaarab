package com.mumtaz.aplikasibahasaarab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    private Button btnShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        btnShow = (Button) findViewById(R.id.showAlert);

        btnShow.setOnClickListener(v -> {

            // Create an alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // set the custom layout
            final View customLayout = getLayoutInflater().inflate(R.layout.alert_dialog_custom, null);
            Button btnLanjutkan = customLayout.findViewById(R.id.btnLanjutkan);

            btnLanjutkan.setText("Lanjutkan");
            builder.setView(customLayout);
            builder.setCancelable(false);


            // add a button

            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();

            btnLanjutkan.setOnClickListener(v1 -> {
                dialog.dismiss();
            });
        });
    }

}
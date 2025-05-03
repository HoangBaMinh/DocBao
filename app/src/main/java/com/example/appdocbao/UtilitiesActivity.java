package com.example.appdocbao;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class UtilitiesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utilities);

        Button btnHome = findViewById(R.id.btn_home);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(UtilitiesActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // btn da xem
        Button btnDaXem = findViewById(R.id.btn_viewed);
        btnDaXem.setOnClickListener(v -> {
            Intent intent = new Intent(UtilitiesActivity.this, ViewedActivity.class);
            startActivity(intent);
        });

        // btn chuyen muc
        Button btnChuyenMuc = findViewById(R.id.btn_category);
        btnChuyenMuc.setOnClickListener(v -> {
            Intent intent = new Intent(UtilitiesActivity.this, CategoryActivity.class);
            startActivity(intent);
        });
    }
}
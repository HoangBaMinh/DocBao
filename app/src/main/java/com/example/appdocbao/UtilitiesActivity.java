package com.example.appdocbao;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UtilitiesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utilities);

//        Button btnHome = findViewById(R.id.btn_home);
//        btnHome.setOnClickListener(v -> {
//            Intent intent = new Intent(UtilitiesActivity.this, MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            finish();
//        });

        // btn da xem
        Button btnDaXem = findViewById(R.id.btn_viewed);
        btnDaXem.setOnClickListener(v -> {
            Intent intent = new Intent(UtilitiesActivity.this, ViewedActivity.class);
            startActivity(intent);
        });

        // btn chuyen muc
//        Button btnChuyenMuc = findViewById(R.id.btn_category);
//        btnChuyenMuc.setOnClickListener(v -> {
//            Intent intent = new Intent(UtilitiesActivity.this, CategoryActivity.class);
//            startActivity(intent);
//        });

        // 3 btn cua cái dưới
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

// Đặt đúng tab được chọn tương ứng với Activity hiện tại
        bottomNavigationView.setSelectedItemId(R.id.tab_util);

// Xử lý chuyển tab
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.tab_home) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.tab_category) {
                Intent intent = new Intent(this, CategoryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.tab_util) {
                return true;
            }

            return false;
        });
    }
}
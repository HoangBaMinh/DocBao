package com.example.appdocbao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import Adapters.NewAdapter;
import Models.New;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewAdapter adapter;
    private List<New> newsList;
    private boolean isLoading = false;
    private int visibleThreshold = 1; // Khi còn 2 item cuối thì tải tiếp

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        recyclerView = findViewById(R.id.rv_news);
        newsList = new ArrayList<>();
        adapter = new NewAdapter(newsList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadInitialData();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView rv, int dx, int dy) {
                super.onScrolled(rv, dx, dy);
                LinearLayoutManager lm = (LinearLayoutManager) rv.getLayoutManager();
                if (!isLoading && lm != null && lm.findLastVisibleItemPosition() >= newsList.size() - visibleThreshold) {
                    isLoading = true;
                    loadMoreNews();
                }
            }
        });



        // 3 btn cua cái dưới
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

// Đặt đúng tab được chọn tương ứng với Activity hiện tại
        bottomNavigationView.setSelectedItemId(R.id.tab_home);

// Xử lý chuyển tab
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.tab_home) {
                // Nếu đã ở MainActivity thì không làm gì cả
                return true;
            } else if (itemId == R.id.tab_category) {
                Intent intent = new Intent(this, CategoryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.tab_util) {
                Intent intent = new Intent(this, UtilitiesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            }

            return false;
        });




//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//
//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            int itemId = item.getItemId();
//
//            if (itemId == R.id.tab_home) {
//                // Chuyển đến HomeActivity
//                Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                startActivity(intent);
//                return true;
//            } else if (itemId == R.id.tab_category) {
//                // Chuyển đến CategoryActivity
//                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
//                startActivity(intent);
//                return true;
//            } else if (itemId == R.id.tab_util) {
//                // Chuyển đến UtilitiesActivity
//                Intent intent = new Intent(MainActivity.this, UtilitiesActivity.class);
//                startActivity(intent);
//                return true;
//            }
//            return false;
//        });



    }

    private void loadInitialData() {
        newsList.addAll(mockNews());
        adapter.notifyDataSetChanged();
    }

    private void loadMoreNews() {
        // Mô phỏng delay tải thêm
        new Handler().postDelayed(() -> {
            adapter.addMore(mockNews());
            isLoading = false;
        }, 1000);
    }

    private List<New> mockNews() {
        List<New> mock = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            mock.add(new New(R.drawable.img, "Tiêu đề " + i, "Nội dung sapo của bài báo " + i));
        }
        return mock;
    }




}
package com.example.appdocbao;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

public class HighlightActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewAdapter adapter;
    private List<New> newsList;
    private boolean isLoading = false;
    private int visibleThreshold = 1; // Khi còn 2 item cuối thì tải tiếp

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_highlight);

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

//        // Điều hướng về Trang chủ
//        Button btnHome = findViewById(R.id.btn_home); // Giả sử "Tin mới" là Trang chủ
//        btnHome.setOnClickListener(v -> {
//            Intent intent = new Intent(HighlightActivity.this, MainActivity.class);
//            startActivity(intent);
//        });
//
//        // Điều hướng đến Tiện ích
//        Button btnUtility = findViewById(R.id.btn_utilities);
//        btnUtility.setOnClickListener(v -> {
//            Intent intent = new Intent(HighlightActivity.this, UtilitiesActivity.class);
//            startActivity(intent);
//        });

        // 3 btn cua cái dưới
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

// Đặt đúng tab được chọn tương ứng với Activity hiện tại
        bottomNavigationView.setSelectedItemId(R.id.tab_category);

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

        // Điều hướng đến Tin mới
        TextView tabHighlights = findViewById(R.id.tab_latest);
        tabHighlights.setOnClickListener(v -> {
            Intent intent = new Intent(HighlightActivity.this, CategoryActivity.class);
            startActivity(intent);
        });


        // sự kiện dấu 3 gạch
        ImageView ivCategory = findViewById(R.id.iv_category);
        ivCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCategoryDialog();
            }
        });

    }

    // xu li rc listview
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


    // xu li hien diago khi an vao 3 gạch
    private void showCategoryDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_category_menu);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        // Nút đóng
        ImageView ivClose = dialog.findViewById(R.id.iv_close);
        ivClose.setOnClickListener(v -> dialog.dismiss());

        // Gắn sự kiện cho các nút trong dialog
        Button btnLatest = dialog.findViewById(R.id.btn_menu_latest);
        Button btnHighlights = dialog.findViewById(R.id.btn_menu_highlights);
        Button btnYourown = dialog.findViewById(R.id.btn_menu_yourown);
        Button btnBusiness = dialog.findViewById(R.id.btn_menu_business);

        btnLatest.setOnClickListener(v -> {
            Intent intent = new Intent(HighlightActivity.this, CategoryActivity.class);
            startActivity(intent);
            dialog.dismiss();
        });

        btnHighlights.setOnClickListener(v -> {
            Intent intent = new Intent(HighlightActivity.this, HighlightActivity.class);
            startActivity(intent);
            dialog.dismiss();
        });

        dialog.show();
    }
}
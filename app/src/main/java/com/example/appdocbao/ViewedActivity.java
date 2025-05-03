package com.example.appdocbao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapters.NewAdapter;
import Models.New;

public class ViewedActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewAdapter adapter;
    private List<New> newsList;
    private boolean isLoading = false;
    private int visibleThreshold = 1; // Khi còn 2 item cuối thì tải tiếp


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewed);

        ImageView ivReturn = findViewById(R.id.iv_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện hành động khi click
                finish(); // Ví dụ: quay lại màn hình trước
            }
        });


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
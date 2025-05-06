package com.example.appdocbao;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapters.ViewedAdapter;
import Models.Viewed;

public class ViewedActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ViewedAdapter adapter;
    private List<Viewed> newsList;
    private boolean isLoading = false;
    private int visibleThreshold = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewed);

        ImageView ivReturn = findViewById(R.id.iv_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Quay lại màn hình trước
            }
        });

        recyclerView = findViewById(R.id.rv_news);
        newsList = new ArrayList<>();
        adapter = new ViewedAdapter(newsList);

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
        new Handler().postDelayed(() -> {
            adapter.addMore(mockNews());
            isLoading = false;
        }, 1000);
    }

    private List<Viewed> mockNews() {
        List<Viewed> mock = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            mock.add(new Viewed("Thể loại " + i, "Tiêu đề " + i, R.drawable.img, "10:0" + i + " AM"));
        }
        return mock;
    }
}

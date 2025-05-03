package com.example.appdocbao;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class Admin_BaoCao extends AppCompatActivity {

    private BarChart barChart;
    private PieChart pieChart;
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_bao_cao);
        barChart = findViewById(R.id.barChart);
        pieChart = findViewById(R.id.pieChart);
        lineChart = findViewById(R.id.lineChart);

        setupBarChart();
        setupPieChart();
        setupLineChart();
    }

    private void setupBarChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 12000));
        entries.add(new BarEntry(1, 9500));
        entries.add(new BarEntry(2, 7200));
        entries.add(new BarEntry(3, 4600));
        entries.add(new BarEntry(4, 3100));

        BarDataSet dataSet = new BarDataSet(entries, "Lượt xem theo chuyên mục");
        dataSet.setColors(Color.parseColor("#FF5722"));
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f);

        barChart.setData(data);
        barChart.setFitBars(true);
        barChart.getDescription().setEnabled(false);
        barChart.invalidate(); // refresh
    }

    private void setupPieChart() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(40f, "Tin mới"));
        entries.add(new PieEntry(25f, "Điểm tin"));
        entries.add(new PieEntry(20f, "Kinh doanh"));
        entries.add(new PieEntry(15f, "Khác"));

        PieDataSet dataSet = new PieDataSet(entries, "Tỷ lệ chuyên mục");
        dataSet.setColors(new int[]{Color.BLUE, Color.MAGENTA, Color.GREEN, Color.GRAY});
        PieData data = new PieData(dataSet);

        pieChart.setData(data);
        pieChart.setUsePercentValues(true);
        Description desc = new Description();
        desc.setText("");
        pieChart.setDescription(desc);
        pieChart.invalidate();
    }

    private void setupLineChart() {
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 5000));
        entries.add(new Entry(2, 8000));
        entries.add(new Entry(3, 7500));
        entries.add(new Entry(4, 11000));
        entries.add(new Entry(5, 9500));

        LineDataSet dataSet = new LineDataSet(entries, "Lượt xem theo tháng");
        dataSet.setColor(Color.RED);
        dataSet.setCircleColor(Color.BLACK);

        LineData data = new LineData(dataSet);
        lineChart.setData(data);
        lineChart.getDescription().setEnabled(false);
        lineChart.invalidate();
    }

}
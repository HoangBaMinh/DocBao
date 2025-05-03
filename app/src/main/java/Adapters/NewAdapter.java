package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdocbao.R;

import java.util.List;

import Models.New;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewsViewHolder> {
    private List<New> newsList;

    public NewAdapter(List<New> newsList) {
        this.newsList = newsList;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView, sapoView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_news);
            titleView = itemView.findViewById(R.id.tv_headline);
            sapoView = itemView.findViewById(R.id.tv_sapo);
        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        New news = newsList.get(position);
        holder.imageView.setImageResource(news.imageResId);
        holder.titleView.setText(news.title);
        holder.sapoView.setText(news.sapo);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void addMore(List<New> moreNews) {
        int start = newsList.size();
        newsList.addAll(moreNews);
        notifyItemRangeInserted(start, moreNews.size());
    }
}


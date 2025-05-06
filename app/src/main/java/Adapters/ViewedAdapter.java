package Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdocbao.R;

import java.util.List;

import Models.Viewed;

public class ViewedAdapter extends RecyclerView.Adapter<ViewedAdapter.NewsViewHolder> {
    private List<Viewed> newsList;

    public ViewedAdapter(List<Viewed> newsList) {
        this.newsList = newsList;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_category, tv_title, tv_time;

        @SuppressLint("WrongViewCast")
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_category = itemView.findViewById(R.id.tv_category_viewed);
            tv_title = itemView.findViewById(R.id.tv_title_viewed);
            imageView = itemView.findViewById(R.id.iv_news_viewed);
            tv_time = itemView.findViewById(R.id.tv_time_viewed);
        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rclistviewviewed, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Viewed news = newsList.get(position);

        holder.tv_category.setText(news.category);
        holder.tv_title.setText(news.title);
        holder.imageView.setImageResource(news.imageResId);
        holder.tv_time.setText(news.time);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void addMore(List<Viewed> moreNews) {
        int start = newsList.size();
        newsList.addAll(moreNews);
        notifyItemRangeInserted(start, moreNews.size());
    }
}

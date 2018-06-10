package com.sproject.ikidz.view.activity.home.main_feature;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.viewObject.News;
import com.sproject.ikidz.sdk.Utils.TextUtils;

import java.util.List;

public class AdapterNews extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<News> newsList;
    Context context;
    LayoutInflater inflater;

    public AdapterNews(List<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(newsList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_news_name, tv_content;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_news_name = itemView.findViewById(R.id.tv_news_name);
            tv_content = itemView.findViewById(R.id.tv_content);
        }

        public void setData(News data) {
            if (!TextUtils.isEmpty(data.getName()))
                tv_news_name.setText(data.getName());
            if (!TextUtils.isEmpty(data.getContent()))
                tv_content.setText(data.getContent());
        }
    }
}

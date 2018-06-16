package com.sproject.ikidz.view.fragment.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.AlbumEntity;
import com.sproject.ikidz.sdk.Utils.TextUtils;
import com.sproject.ikidz.sdk.Utils.WidgetUtils;
import com.sproject.ikidz.sdk.callback.ImageItemClick;
import com.squareup.picasso.Callback;

import java.util.ArrayList;
import java.util.List;

public class AdapterImageNews extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<AlbumEntity> data;
    Context context;
    ImageItemClick listener;

    public AdapterImageNews(Context context, ImageItemClick listener) {
        data = new ArrayList<>();
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(data.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setListData(List<AlbumEntity> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_item;
        ProgressBar prg_loading;

        public ViewHolder(View itemView) {
            super(itemView);
            img_item = itemView.findViewById(R.id.item_image);
            prg_loading = itemView.findViewById(R.id.prg_loading);
        }

        public void setData(AlbumEntity entity, int position) {
            if (!TextUtils.isEmpty(entity.getImage())) {
                WidgetUtils.setImageNotBaseURL(img_item, entity.getImage(), R.mipmap.ic_launcher, new Callback() {
                    @Override
                    public void onSuccess() {
                        prg_loading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
                itemView.setOnClickListener(view -> listener.onClickItem(position));
            }
        }
    }
}

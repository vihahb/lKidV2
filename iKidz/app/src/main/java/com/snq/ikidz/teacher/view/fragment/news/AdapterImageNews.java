package com.snq.ikidz.teacher.view.fragment.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.snq.ikidz.teacher.R;
import com.snq.ikidz.teacher.iKidApplications;
import com.snq.ikidz.teacher.model.entity.AlbumEntity;
import com.snq.ikidz.teacher.sdk.Utils.TextUtils;
import com.snq.ikidz.teacher.sdk.callback.ImageItemClick;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterImageNews extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "AdapterImageNews";

    List<AlbumEntity> data;
    Context context;
    ImageItemClick listener;

    public AdapterImageNews(List<AlbumEntity> data, Context context, ImageItemClick listener) {
        this.data = data;
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
        if (data != null)
            return data.size();
        else return 0;
    }

//    public void setListData(List<AlbumEntity> data) {
//        if (data != null) {
//            this.data.clear();
//            this.data.addAll(data);
//            notifyDataSetChanged();
//        }
//    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_item;

        public ViewHolder(View itemView) {
            super(itemView);
            img_item = itemView.findViewById(R.id.item_image);
        }

        public void setData(AlbumEntity entity, int position) {
            if (!TextUtils.isEmpty(entity.getImage())) {
                Picasso.with(context).load(entity.getImage()).resize(200, 200).centerCrop().into(img_item, new Callback() {
                    @Override
                    public void onSuccess() {
                        iKidApplications.log(TAG, "Load image success");
                    }

                    @Override
                    public void onError() {
                        iKidApplications.log(TAG, "Load image success");
                    }
                });
                itemView.setOnClickListener(view -> {
                    listener.onClickItem(data, position);
                });
            }
        }
    }
}

package com.sproject.ikidz.view.activity.learnProgram.viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.ContentEntity;
import com.sproject.ikidz.sdk.Utils.TextUtils;
import com.sproject.ikidz.sdk.callback.ItemMoreActionGeneric;

import java.util.List;

public class AdapterLearnProgram extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ContentEntity> data;
    Context context;
    ItemMoreActionGeneric<ContentEntity> listener;

    public AdapterLearnProgram(List<ContentEntity> data, Context context, ItemMoreActionGeneric<ContentEntity> listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_data_week, parent, false));
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

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_day, tv_content, tv_content_2;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_day = itemView.findViewById(R.id.tv_day);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_content_2 = itemView.findViewById(R.id.tv_content_2);
        }

        public void setData(ContentEntity entity, int position) {
            tv_day.setText("Thứ " + entity.getDay() + " - Ngày " + entity.getDate());

            if (!TextUtils.isEmpty(entity.getContent())) {
                String[] arr = entity.getContent().split("</span>");
                String content = arr[0] + arr[1];
                String content_2 = arr[2] + arr[3];
                tv_content.setText(Html.fromHtml(content));
                tv_content_2.setText(Html.fromHtml(content_2));
            }
        }
    }
}
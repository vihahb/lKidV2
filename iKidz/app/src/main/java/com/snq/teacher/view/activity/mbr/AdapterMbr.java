package com.snq.teacher.view.activity.mbr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snq.teacher.R;
import com.snq.teacher.model.entity.StudentsEntity;
import com.snq.teacher.sdk.Utils.RoundImage;
import com.snq.teacher.sdk.Utils.TextUtils;
import com.snq.teacher.sdk.Utils.WidgetUtils;
import com.snq.teacher.sdk.callback.ItemClickListenerGeneric;

import java.util.List;

public class AdapterMbr extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<StudentsEntity> list;
    Context context;
    ItemClickListenerGeneric<StudentsEntity> listener;


    public AdapterMbr(List<StudentsEntity> list, Context context, ItemClickListenerGeneric<StudentsEntity> listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_mbr, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(list.get(position), position);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_number_row, tv_name;
        RoundImage img_ava;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number_row = itemView.findViewById(R.id.tv_number_row);
            tv_name = itemView.findViewById(R.id.tv_name);
            img_ava = itemView.findViewById(R.id.img_avatar);
        }

        public void setData(StudentsEntity data, int position) {
            if (!TextUtils.isEmpty(data.getFullName())) {
                tv_name.setText(data.getFullName());
            }

            tv_number_row.setText("" + (position + 1));

            WidgetUtils.setImageURL(img_ava, data.getAvatar(), R.mipmap.ic_launcher);
            itemView.setOnClickListener(view -> listener.ItemClick(position, data));
        }
    }
}
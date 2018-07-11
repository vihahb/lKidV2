package com.snq.teacher.view.activity.curentClass;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snq.teacher.R;
import com.snq.teacher.model.entity.StudentEntity;
import com.snq.teacher.sdk.Utils.RoundImage;
import com.snq.teacher.sdk.Utils.TextUtils;
import com.snq.teacher.sdk.Utils.WidgetUtils;
import com.snq.teacher.sdk.callback.ItemClickListener;

import java.util.List;

public class AdapterCurrentClass extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<StudentEntity> data;
    Context context;
    ItemClickListener listener;

    public AdapterCurrentClass(List<StudentEntity> data, Context context, ItemClickListener listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_class, parent, false));
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

    public void filterList(List<StudentEntity> filterdNames) {
        this.data = filterdNames;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_number_row, tv_student_name, tv_birthday;
        RoundImage img_ava;
        ImageView img_more, img_dollar, img_eyes, img_disease_monitor, img_user;
        boolean click_more = false;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number_row = itemView.findViewById(R.id.tv_number_row);
            tv_student_name = itemView.findViewById(R.id.tv_student_name);
            tv_birthday = itemView.findViewById(R.id.tv_birthday);
            img_ava = itemView.findViewById(R.id.img_avatar);
            img_more = itemView.findViewById(R.id.img_more);
            img_dollar = itemView.findViewById(R.id.img_dollar);
            img_eyes = itemView.findViewById(R.id.img_eyes);
            img_disease_monitor = itemView.findViewById(R.id.img_disease_monitor);
            img_user = itemView.findViewById(R.id.img_user);
        }

        public void setData(StudentEntity entity, int position) {
            tv_number_row.setText("" + (position + 1));
            if (!TextUtils.isEmpty(entity.getFullName()))
                tv_student_name.setText(entity.getFullName());

            if (!TextUtils.isEmpty(entity.getFullName()))
                tv_birthday.setText(entity.getBirthday());

            img_more.setOnClickListener(view -> {
                if (!click_more) {
                    click_more = true;
                } else {
                    click_more = false;
                }
                setVisibleAction(click_more);
            });
            WidgetUtils.setImageURL(img_ava, "/" + entity.getAvatar(), R.mipmap.ic_launcher_round);
            itemView.setOnClickListener(view -> listener.ItemClick(position));
        }

        private void setVisibleAction(boolean click_more) {
            if (click_more) {
                img_dollar.setVisibility(View.VISIBLE);
                img_eyes.setVisibility(View.VISIBLE);
                img_disease_monitor.setVisibility(View.VISIBLE);
                img_user.setVisibility(View.VISIBLE);
            } else {
                img_dollar.setVisibility(View.INVISIBLE);
                img_eyes.setVisibility(View.INVISIBLE);
                img_disease_monitor.setVisibility(View.INVISIBLE);
                img_user.setVisibility(View.INVISIBLE);
            }
        }
    }
}

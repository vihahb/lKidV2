package com.snq.teacher.view.activity.sleepActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.snq.teacher.R;
import com.snq.teacher.model.entity.SleepEntity;
import com.snq.teacher.sdk.Utils.RoundImage;
import com.snq.teacher.sdk.Utils.TextUtils;
import com.snq.teacher.sdk.Utils.WidgetUtils;
import com.snq.teacher.sdk.callback.ItemClickListenerGeneric;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterSleep extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<SleepEntity> data;
    Context context;
    ItemClickListenerGeneric<SleepEntity> listener;

    public AdapterSleep(List<SleepEntity> data, Context context, ItemClickListenerGeneric<SleepEntity> listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sleep_activity, parent, false));
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

    public void filterList(@NotNull List<SleepEntity> filterdNames) {
        this.data = filterdNames;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_number_row, tv_name;
        RoundImage img_avatar;
        EditText edt_time_start, edt_time_end, edt_note;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number_row = itemView.findViewById(R.id.tv_number_row);
            tv_name = itemView.findViewById(R.id.tv_name);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            edt_time_start = itemView.findViewById(R.id.edt_time_start);
            edt_time_end = itemView.findViewById(R.id.edt_time_end);
            edt_note = itemView.findViewById(R.id.edt_note);
        }

        public void setData(SleepEntity entity, int position) {
            if (!TextUtils.isEmpty(entity.getFullName())) {
                tv_name.setText(entity.getFullName());
            }
            if (!TextUtils.isEmpty(entity.getStartTime())) {
                edt_time_start.setText(entity.getStartTime());
            }
            if (!TextUtils.isEmpty(entity.getEndTime())) {
                edt_time_end.setText(entity.getEndTime());
            }

            if (!TextUtils.isEmpty(entity.getNote())) {
                edt_note.setText(entity.getNote());
            }
            if (!TextUtils.isEmpty(entity.getAvatar())) {
                WidgetUtils.setImageURL(img_avatar, entity.getAvatar(), R.mipmap.ic_launcher_round);
            }
            tv_number_row.setText("" + (position + 1));


            itemView.setOnClickListener(view -> listener.ItemClick(position, entity));
        }
    }
}
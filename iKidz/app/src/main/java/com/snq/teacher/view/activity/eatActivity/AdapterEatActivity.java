package com.snq.teacher.view.activity.eatActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.snq.teacher.R;
import com.snq.teacher.model.entity.EatEntity;
import com.snq.teacher.sdk.Utils.RoundImage;
import com.snq.teacher.sdk.Utils.TextUtils;
import com.snq.teacher.sdk.Utils.WidgetUtils;
import com.snq.teacher.sdk.callback.ItemClickListenerGeneric;

import java.util.List;

public class AdapterEatActivity extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<EatEntity> list;
    Context context;
    ItemClickListenerGeneric<EatEntity> listener;


    public AdapterEatActivity(List<EatEntity> list, Context context, ItemClickListenerGeneric<EatEntity> listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_eat_activity, parent, false));
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

        TextView tv_number_row, tv_name, tv_eat_state;
        EditText edt_note;
        RoundImage img_ava;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number_row = itemView.findViewById(R.id.tv_number_row);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_eat_state = itemView.findViewById(R.id.tv_eat_state);
            img_ava = itemView.findViewById(R.id.img_avatar);
            edt_note = itemView.findViewById(R.id.tv_eat_note);
        }

        public void setData(EatEntity data, int position) {
            if (!TextUtils.isEmpty(data.getFullName())) {
                tv_name.setText(data.getFullName());
            }

            if (!TextUtils.isEmpty(data.getEatStatus())) {
                tv_eat_state.setVisibility(View.VISIBLE);
                tv_eat_state.setText(data.getEatStatus());
            } else {
                tv_eat_state.setVisibility(View.INVISIBLE);
            }

            if (!TextUtils.isEmpty(data.getNote())) {
                edt_note.setText(data.getNote());
            }

            tv_number_row.setText("" + (position + 1));

            WidgetUtils.setImageURL(img_ava, data.getAvatar(), R.mipmap.ic_no_avatar);

            itemView.setOnClickListener(view -> listener.ItemClick(position, data));
        }
    }
}
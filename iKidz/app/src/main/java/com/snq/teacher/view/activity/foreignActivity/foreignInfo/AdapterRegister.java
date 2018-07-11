package com.snq.teacher.view.activity.foreignActivity.foreignInfo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snq.teacher.R;
import com.snq.teacher.model.entity.RegisterEntity;
import com.snq.teacher.sdk.Utils.RoundImage;
import com.snq.teacher.sdk.Utils.TextUtils;
import com.snq.teacher.sdk.Utils.WidgetUtils;

import java.util.List;

public class AdapterRegister extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<RegisterEntity> data;
    Context context;

    public AdapterRegister(List<RegisterEntity> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_register, parent, false));
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

        TextView tv_number_row, tv_register_name;
        RoundImage img_ava;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number_row = itemView.findViewById(R.id.tv_number_row);
            tv_register_name = itemView.findViewById(R.id.tv_register_name);
            img_ava = itemView.findViewById(R.id.img_avatar);
        }

        public void setData(RegisterEntity entity, int position) {
            tv_number_row.setText("" + (position + 1));

            String info_user = "";

            if (!TextUtils.isEmpty(entity.getFullName())) {
                info_user = entity.getFullName();
            }
            if (!TextUtils.isEmpty(entity.getBirthday())) {
                info_user += " (" + entity.getBirthday() + ")";
            }
            tv_register_name.setText(info_user);

            if (!TextUtils.isEmpty(entity.getAvatarStudent())) {
                WidgetUtils.setImageURL(img_ava, entity.getAvatarStudent(), R.mipmap.ic_launcher_round);
            }
        }
    }
}

package com.sproject.ikidz.view.activity.subscribeToEat.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.EatTicketEntity;
import com.sproject.ikidz.sdk.Utils.RoundImage;
import com.sproject.ikidz.sdk.Utils.TextUtils;
import com.sproject.ikidz.sdk.Utils.WidgetUtils;
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric;

import java.util.List;

public class AdapterSubcribeEat extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<EatTicketEntity> data;
    Context context;
    ItemClickListenerGeneric<EatTicketEntity> listener;

    public AdapterSubcribeEat(List<EatTicketEntity> data, Context context, ItemClickListenerGeneric<EatTicketEntity> listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_subcribe_to_eat, parent, false));
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

        TextView tv_number_row, tv_name, tv_date, tv_state;
        RoundImage img_avatar;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number_row = itemView.findViewById(R.id.tv_number_row);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            tv_state = itemView.findViewById(R.id.tv_state);
        }

        public void setData(EatTicketEntity entity, int position) {
            if (!TextUtils.isEmpty(entity.getFullNameStudent())) {
                tv_name.setText(entity.getFullNameStudent());
            }
            if (!TextUtils.isEmpty(entity.getBirthday())) {
                tv_date.setText(entity.getBirthday());
            }
            if (!TextUtils.isEmpty(entity.getAvatarStudent())) {
                WidgetUtils.setImageURL(img_avatar, entity.getAvatarStudent(), R.mipmap.ic_launcher_round);
            }
            tv_number_row.setText("" + (position + 1));
            switch (entity.getIsRegister()) {
                case "0":
                    tv_state.setText("Chưa đăng ký");
                    tv_state.setBackgroundResource(R.drawable.round_red_color);
                    break;
                case "1":
                    tv_state.setText("Đã đăng ký");
                    tv_state.setBackgroundResource(R.drawable.round_green_color);
                    break;
            }

            itemView.setOnClickListener(view -> listener.ItemClick(position, entity));
        }
    }
}
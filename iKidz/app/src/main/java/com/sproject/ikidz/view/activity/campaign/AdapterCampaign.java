package com.sproject.ikidz.view.activity.campaign;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.CampainEntity;
import com.sproject.ikidz.sdk.Utils.TextUtils;
import com.sproject.ikidz.sdk.Utils.TimeUtils;
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric;

import java.util.List;

public class AdapterCampaign extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<CampainEntity> data;
    Context context;
    ItemClickListenerGeneric<CampainEntity> listener;

    public AdapterCampaign(List<CampainEntity> data, Context context, ItemClickListenerGeneric<CampainEntity> listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_campaign, parent, false));
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

        TextView tv_title, tv_time;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_time = itemView.findViewById(R.id.tv_time);
        }

        public void setData(CampainEntity entity, int position) {
            if (!TextUtils.isEmpty(entity.getName())) {
                tv_title.setText((position + 1) + ". " + entity.getName());
            }

            String date = "";

            if (!TextUtils.isEmpty(entity.getDateStart())) {
                date = "Bắt đầu: " + TimeUtils.getInstance().formatDate(entity.getDateStart(), "yyyy-MM-dd", "dd/MM/yyyy");
            }

            if (!TextUtils.isEmpty(entity.getDateEnd())) {
                date += " - Kết thúc: " + TimeUtils.getInstance().formatDate(entity.getDateEnd(), "yyyy-MM-dd", "dd/MM/yyyy");
            }

            tv_time.setText(date);

            itemView.setOnClickListener(view -> listener.ItemClick(position, entity));
        }
    }
}
package com.sproject.ikidz.view.activity.shuttleBus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.CampainEntity;
import com.sproject.ikidz.model.entity.EatTicketEntity;
import com.sproject.ikidz.model.entity.LearnActivityEntity;
import com.sproject.ikidz.model.entity.ShuttleBusEntity;
import com.sproject.ikidz.model.entity.ShuttlepickupPersonEntity;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.RoundImage;
import com.sproject.ikidz.sdk.Utils.TextUtils;
import com.sproject.ikidz.sdk.Utils.TimeUtil;
import com.sproject.ikidz.sdk.Utils.TimeUtils;
import com.sproject.ikidz.sdk.Utils.WidgetUtils;
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric;
import com.sproject.ikidz.sdk.callback.ItemMoreActionGeneric;

import java.util.List;

public class AdapterShuttlesBus extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ShuttleBusEntity> data;
    Context context;
    ItemClickListenerGeneric<ShuttleBusEntity> listener;

    public AdapterShuttlesBus(List<ShuttleBusEntity> data, Context context, ItemClickListenerGeneric<ShuttleBusEntity> listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_shuttles_bus, parent, false));
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

        TextView tv_number_row, tv_name, tv_date;
        RoundImage img_avatar;
        ImageView img_type, img_group;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number_row = itemView.findViewById(R.id.tv_number_row);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            img_type = itemView.findViewById(R.id.img_type);
            img_group = itemView.findViewById(R.id.img_group);
        }

        public void setData(ShuttleBusEntity entity, int position) {
            if (!TextUtils.isEmpty(entity.getFullName())) {
                tv_name.setText(entity.getFullName());
            }
            if (!TextUtils.isEmpty(entity.getBirthday())) {
                tv_date.setText(entity.getBirthday());
            }
            if (!TextUtils.isEmpty(entity.getAvatarStudent())) {
                WidgetUtils.setImageURL(img_avatar, entity.getAvatarStudent(), R.mipmap.ic_launcher_round);
            }
            tv_number_row.setText("" + (position + 1));
            switch (entity.getTypePickup()) {
                case "school":
                    img_type.setImageResource(R.mipmap.ic_nt);
                    break;
                case "parent":
                    img_type.setImageResource(R.mipmap.ic_ph);
                    break;
            }

            itemView.setOnClickListener(view -> listener.ItemClick(position, entity));
        }
    }
}



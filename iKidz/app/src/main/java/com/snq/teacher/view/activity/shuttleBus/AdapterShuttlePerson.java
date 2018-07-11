package com.snq.teacher.view.activity.shuttleBus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snq.teacher.R;
import com.snq.teacher.model.entity.ShuttlepickupPersonEntity;
import com.snq.teacher.sdk.Utils.RoundImage;
import com.snq.teacher.sdk.Utils.TextUtils;
import com.snq.teacher.sdk.Utils.WidgetUtils;
import com.snq.teacher.sdk.callback.ItemClickListenerGeneric;

import java.util.List;

public class AdapterShuttlePerson extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ShuttlepickupPersonEntity> data;
    Context context;
    ItemClickListenerGeneric<ShuttlepickupPersonEntity> listener;

    public AdapterShuttlePerson(List<ShuttlepickupPersonEntity> data, Context context, ItemClickListenerGeneric<ShuttlepickupPersonEntity> listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_shuttle_person, parent, false));
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

        TextView tv_phone, tv_name, tv_relation;
        RoundImage img_avatar;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_relation = itemView.findViewById(R.id.tv_relation);
            img_avatar = itemView.findViewById(R.id.img_avatar);
        }

        public void setData(ShuttlepickupPersonEntity entity, int position) {
            if (!TextUtils.isEmpty(entity.getName())) {
                String name = "<b>Họ tên:</b> " + entity.getName();
                tv_name.setText(Html.fromHtml(name));
            }
            if (!TextUtils.isEmpty(entity.getRelationshipName())) {
                String relation = "<b>Mối quan hệ:</b> " + entity.getRelationshipName();
                tv_relation.setText(Html.fromHtml(relation));
            }
            if (!TextUtils.isEmpty(entity.getPhone())) {
                String phone = "<b>Số điện thoại:</b> " + entity.getPhone();
                tv_phone.setText(Html.fromHtml(phone));
            }
            if (!TextUtils.isEmpty(entity.getImage())) {
                WidgetUtils.setImageURL(img_avatar, entity.getImage(), R.mipmap.ic_launcher_round);
            }
            itemView.setOnClickListener(view -> listener.ItemClick(position, entity));
        }
    }
}

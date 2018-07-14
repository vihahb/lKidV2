package com.snq.ikidz.teacher.view.fragment.message;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snq.ikidz.teacher.R;
import com.snq.ikidz.teacher.model.message.MessageView;
import com.snq.ikidz.teacher.sdk.Utils.RoundImage;
import com.snq.ikidz.teacher.sdk.Utils.TextUtils;
import com.snq.ikidz.teacher.sdk.Utils.WidgetUtils;
import com.snq.ikidz.teacher.sdk.callback.ItemClickListenerGeneric;

import java.util.List;

public class AdapterMessageList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<MessageView> data;
    Context context;
    ItemClickListenerGeneric<MessageView> listener;

    public AdapterMessageList(List<MessageView> data, Context context, ItemClickListenerGeneric<MessageView> listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_message_list, parent, false));
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

        TextView tv_username, tv_message, tv_time;
        RoundImage img_avatar;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_message = itemView.findViewById(R.id.tv_message);
            tv_time = itemView.findViewById(R.id.tv_time);
            img_avatar = itemView.findViewById(R.id.img_avatar);
        }

        public void setData(MessageView entity, int position) {
            if (!TextUtils.isEmpty(entity.getUser().getName())) {
                tv_username.setText(entity.getUser().getName());
            }
            if (!TextUtils.isEmpty(entity.getLastMessage().getMessage())) {
                tv_message.setText(entity.getLastMessage().getMessage());
            }

            if (entity.getMetadata().getCreatedAt() != null && entity.getMetadata().getCreatedAt() > 0) {
                tv_time.setText(TextUtils.comparingTime(entity.getMetadata().getCreatedAt()));
            }

            if (!TextUtils.isEmpty(entity.getUser().getAvatar())) {
                WidgetUtils.setImageURL(img_avatar, entity.getUser().getAvatar(), R.mipmap.ic_launcher_round);
            }

        }
    }
}
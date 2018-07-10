package com.sproject.ikidz.view.activity.otherActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.OtherActivitysEntity;
import com.sproject.ikidz.sdk.Utils.RoundImage;
import com.sproject.ikidz.sdk.Utils.TextUtils;
import com.sproject.ikidz.sdk.Utils.WidgetUtils;
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterOtherActivitys extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<OtherActivitysEntity> data;
    Context context;
    ItemClickListenerGeneric<OtherActivitysEntity> listener;

    public AdapterOtherActivitys(List<OtherActivitysEntity> data, Context context, ItemClickListenerGeneric<OtherActivitysEntity> listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_other_activitys, parent, false));
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

    public void filterList(@NotNull List<OtherActivitysEntity> filterdNames) {
        this.data = filterdNames;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_number_row, tv_name;
        RoundImage img_avatar;
        EditText edt_note;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number_row = itemView.findViewById(R.id.tv_number_row);
            tv_name = itemView.findViewById(R.id.tv_name);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            edt_note = itemView.findViewById(R.id.edt_note);
        }

        public void setData(OtherActivitysEntity entity, int position) {
            if (!TextUtils.isEmpty(entity.getFullName())) {
                tv_name.setText(entity.getFullName());
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

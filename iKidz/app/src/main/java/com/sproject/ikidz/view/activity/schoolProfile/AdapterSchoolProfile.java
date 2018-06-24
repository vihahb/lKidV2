package com.sproject.ikidz.view.activity.schoolProfile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.ProfileEntity;
import com.sproject.ikidz.sdk.Utils.RoundImage;
import com.sproject.ikidz.sdk.Utils.TextUtils;
import com.sproject.ikidz.sdk.Utils.WidgetUtils;
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric;

import java.util.List;

public class AdapterSchoolProfile extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ProfileEntity> data;
    Context context;
    ItemClickListenerGeneric<ProfileEntity> listener;

    public AdapterSchoolProfile(List<ProfileEntity> data, Context context, ItemClickListenerGeneric<ProfileEntity> listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_school_profile, parent, false));
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

        TextView tv_number_row, tv_name, tv_date, tv_write_comment;
        RoundImage img_avatar;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number_row = itemView.findViewById(R.id.tv_number_row);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            tv_write_comment = itemView.findViewById(R.id.tv_write_comment);
        }

        public void setData(ProfileEntity entity, int position) {
            if (!TextUtils.isEmpty(entity.getFullName())) {
                tv_name.setText(entity.getFullName());
            }
            if (!TextUtils.isEmpty(entity.getBirthday())) {
                tv_date.setText(entity.getBirthday());
            }
            if (!TextUtils.isEmpty(entity.getAvatar())) {
                WidgetUtils.setImageURL(img_avatar, entity.getAvatar(), R.mipmap.ic_launcher_round);
            }
            tv_number_row.setText("" + (position + 1));

            tv_write_comment.setOnClickListener(view -> listener.ItemClick(position, entity));
        }
    }
}
package com.snq.ikidz.teacher.view.activity.foreignActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snq.ikidz.teacher.R;
import com.snq.ikidz.teacher.iKidApplications;
import com.snq.ikidz.teacher.model.entity.ForeignActivityEntity;
import com.snq.ikidz.teacher.sdk.Utils.TextUtils;
import com.snq.ikidz.teacher.sdk.callback.ItemClickListenerGeneric;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterForeignActivity extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "AdapterForeignActivity";

    List<ForeignActivityEntity> data;
    Context context;
    ItemClickListenerGeneric listener;

    public AdapterForeignActivity(List<ForeignActivityEntity> data, Context context, ItemClickListenerGeneric listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_foreign_activity, parent, false));
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

        ImageView img_thumble;
        TextView tv_title, tv_count_user, tv_date_start;

        public ViewHolder(View itemView) {
            super(itemView);
            img_thumble = itemView.findViewById(R.id.img_thumble);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_count_user = itemView.findViewById(R.id.tv_count_user);
            tv_date_start = itemView.findViewById(R.id.tv_date_start);
        }

        public void setData(ForeignActivityEntity entity, int position) {
            if (!TextUtils.isEmpty(entity.getName())) {
                tv_title.setText(entity.getName());
            }

            if (!TextUtils.isEmpty(entity.getCountRegister())) {
                tv_count_user.setText(entity.getCountRegister() + " người tham gia");
            }

            if (!TextUtils.isEmpty(entity.getDate())) {
                tv_date_start.setText("Hạn đăng ký: " + entity.getDate());
            }

            if (!TextUtils.isEmpty(entity.getImage())) {
                Picasso.with(iKidApplications.context)
                        .load(entity.getImage())
                        .noPlaceholder()
                        .error(R.drawable.ic_load_image_error)
                        .into(img_thumble, new Callback() {
                            @Override
                            public void onSuccess() {
                                iKidApplications.log(TAG, "Load image success url: " + entity.toString());
                            }

                            @Override
                            public void onError() {
                                iKidApplications.log(TAG, "Load image error url: " + entity.toString());
                            }
                        });
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.ItemClick(position, entity);
                }
            });

        }
    }
}

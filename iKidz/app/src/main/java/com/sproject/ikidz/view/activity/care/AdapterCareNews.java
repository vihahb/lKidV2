package com.sproject.ikidz.view.activity.care;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.iKidApplications;
import com.sproject.ikidz.model.entity.CareNewsEntity;
import com.sproject.ikidz.sdk.Utils.TextUtils;
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterCareNews extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "AdapterSchoolsNews";

    List<CareNewsEntity> data;
    Context context;
    ItemClickListenerGeneric<CareNewsEntity> listener;

    public AdapterCareNews(List<CareNewsEntity> data, Context context, ItemClickListenerGeneric<CareNewsEntity> listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_care_baby, parent, false));
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

    public void filterList(List<CareNewsEntity> filterdNames) {
        this.data = filterdNames;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_thumble;
        TextView tv_title, tv_time, tv_view;

        public ViewHolder(View itemView) {
            super(itemView);
            img_thumble = itemView.findViewById(R.id.img_thumble);
            tv_view = itemView.findViewById(R.id.tv_view);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_title = itemView.findViewById(R.id.tv_title);
        }

        public void setData(CareNewsEntity entity, int position) {
            tv_view.setText(entity.getTotalView() + " lượt xem");

            if (!TextUtils.isEmpty(entity.getPublishDate())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(sdf.parse(entity.getPublishDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tv_time.setText(TextUtils.comparingTime(calendar.getTimeInMillis()));
            }

            if (!TextUtils.isEmpty(entity.getTitle()))
                tv_title.setText(entity.getTitle());

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

            itemView.setOnClickListener(v -> listener.ItemClick(position, entity));
        }
    }
}
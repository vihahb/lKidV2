package com.snq.teacher.view.activity.school;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snq.teacher.R;
import com.snq.teacher.iKidApplications;
import com.snq.teacher.model.entity.NewsEntity;
import com.snq.teacher.sdk.Utils.TextUtils;
import com.snq.teacher.sdk.callback.ItemClickListenerGeneric;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterSchoolsNews extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "AdapterSchoolsNews";

    List<NewsEntity> data;
    Context context;
    ItemClickListenerGeneric<NewsEntity> listener;

    public AdapterSchoolsNews(List<NewsEntity> data, Context context, ItemClickListenerGeneric<NewsEntity> listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_schools_news, parent, false));
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

    public void filterList(List<NewsEntity> filterdNames) {
        this.data = filterdNames;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_thumble, img_like, img_comment;
        TextView tv_title, tv_date, tv_like, tv_comment;

        public ViewHolder(View itemView) {
            super(itemView);
            img_thumble = itemView.findViewById(R.id.img_thumble);
            img_like = itemView.findViewById(R.id.img_like);
            img_comment = itemView.findViewById(R.id.img_comment);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_like = itemView.findViewById(R.id.tv_like);
            tv_comment = itemView.findViewById(R.id.tv_comment);
        }

        public void setData(NewsEntity entity, int position) {
            if (!TextUtils.isEmpty(entity.getCountComment()))
                tv_like.setText(entity.getCountLike() + " lượt thích");

            if (!TextUtils.isEmpty(entity.getCountComment()))
                tv_comment.setText(entity.getCountComment() + " bình luận");

            if (!TextUtils.isEmpty(entity.getPublishDate()))
                tv_date.setText(entity.getPublishDate());

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

package com.snq.teacher.view.activity.commentDay;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.snq.teacher.R;
import com.snq.teacher.model.entity.CommentLearnEntity;
import com.snq.teacher.sdk.Utils.RoundImage;
import com.snq.teacher.sdk.Utils.TextUtils;
import com.snq.teacher.sdk.Utils.WidgetUtils;
import com.snq.teacher.sdk.callback.ItemClickListenerGeneric;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterLearnCmt extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<CommentLearnEntity> data;
    Context context;
    ItemClickListenerGeneric<CommentLearnEntity> listener;

    public AdapterLearnCmt(List<CommentLearnEntity> data, Context context, ItemClickListenerGeneric<CommentLearnEntity> listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_learn_cmt, parent, false));
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

    public void filterList(@NotNull List<CommentLearnEntity> filterdNames) {
        this.data = filterdNames;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_number_row, tv_name;
        RoundImage img_avatar;
        EditText edt_note;
        ImageView img_title_1, img_title_2;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number_row = itemView.findViewById(R.id.tv_number_row);
            tv_name = itemView.findViewById(R.id.tv_name);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            img_title_1 = itemView.findViewById(R.id.img_title_1);
            img_title_2 = itemView.findViewById(R.id.img_title_2);
            edt_note = itemView.findViewById(R.id.edt_note);
        }

        public void setData(CommentLearnEntity entity, int position) {
            if (!TextUtils.isEmpty(entity.getFullName())) {
                tv_name.setText(entity.getFullName());
            }
            if (!TextUtils.isEmpty(entity.getTitle1())) {
                WidgetUtils.setImageURL(img_title_1, entity.getTitle1(), R.mipmap.ic_no_avatar);
            }

            if (!TextUtils.isEmpty(entity.getTitle1())) {
                WidgetUtils.setImageURL(img_title_1, entity.getTitle1(), R.mipmap.ic_no_avatar);
            }
            if (!TextUtils.isEmpty(entity.getReviewStudy())) {
                edt_note.setText(entity.getReviewStudy());
            }
            if (!TextUtils.isEmpty(entity.getAvatar())) {
                WidgetUtils.setImageURL(img_avatar, entity.getAvatar(), R.mipmap.ic_launcher_round);
            }
            tv_number_row.setText("" + (position + 1));


            itemView.setOnClickListener(view -> listener.ItemClick(position, entity));
        }
    }
}
package com.sproject.ikidz.view.activity.news.all;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.CommentEntity;
import com.sproject.ikidz.sdk.Utils.RoundImage;
import com.sproject.ikidz.sdk.Utils.TextUtils;
import com.sproject.ikidz.sdk.Utils.WidgetUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterAllNews extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<CommentEntity> data;
    Context context;

    public AdapterAllNews(List<CommentEntity> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news_comment, parent, false));
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

        TextView tv_name_time, tv_comment;
        RoundImage img_avatar;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name_time = itemView.findViewById(R.id.tv_name_time);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            img_avatar = itemView.findViewById(R.id.img_avatar);
        }

        public void setData(CommentEntity entity, int position) {
            String dataTime = "";

            if (!TextUtils.isEmpty(entity.getFullName())) {
                dataTime = "<font color=\'#25ADC2\'>" + entity.getFullName() + "</font>";
            }

            if (!TextUtils.isEmpty(data.get(position).getCreatedAt())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(sdf.parse(data.get(position).getCreatedAt()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                dataTime += " - " + TextUtils.comparingTime(calendar.getTimeInMillis());
            }
            tv_name_time.setText(Html.fromHtml(dataTime));

            if (!TextUtils.isEmpty(entity.getComment())) {
                tv_comment.setText(entity.getComment());
            }
            if (!TextUtils.isEmpty(entity.getAvatar())) {
                WidgetUtils.setImageURL(img_avatar, entity.getAvatar(), R.mipmap.ic_launcher_round);
            }
        }
    }
}
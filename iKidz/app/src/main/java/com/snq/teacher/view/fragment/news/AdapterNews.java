package com.snq.teacher.view.fragment.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snq.teacher.R;
import com.snq.teacher.model.entity.AlbumEntity;
import com.snq.teacher.model.entity.NewsEntity;
import com.snq.teacher.sdk.Utils.RoundImage;
import com.snq.teacher.sdk.Utils.TextUtils;
import com.snq.teacher.sdk.Utils.WidgetUtils;
import com.snq.teacher.sdk.callback.ImageItemClick;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.ViewHolder> {
    static ImageItemClick imageItemClick;
    List<NewsEntity> newsList;
    Context context;
    LayoutInflater inflater;

    public AdapterNews(List<NewsEntity> newsList, Context context, ImageItemClick imageItemClick) {
        this.newsList = newsList;
        this.context = context;
        this.imageItemClick = imageItemClick;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_layout_v2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.setData(newsList.get(position));

        if (!TextUtils.isEmpty(newsList.get(position).getFullName()))
            holder.tv_news_name.setText(newsList.get(position).getFullName());
        if (!TextUtils.isEmpty(newsList.get(position).getAvatar()))
            WidgetUtils.setImageURL(holder.img_avatar, newsList.get(position).getAvatar(), R.mipmap.ic_launcher_round);


        if (!TextUtils.isEmpty(newsList.get(position).getPublishDate())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(sdf.parse(newsList.get(position).getPublishDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            holder.tv_time.setText(TextUtils.comparingTime(calendar.getTimeInMillis()));
        }

        if (!TextUtils.isEmpty(newsList.get(position).getTitle()))
            holder.tv_content.setText(newsList.get(position).getTitle());


//        holder.adapterImageNews.setListData(newsList.get(position).getAlbum());
        holder.tv_like.setText(newsList.get(position).getCountLike() + " lượt thích");
        holder.tv_comment.setText(newsList.get(position).getCountComment() + " bình luận");
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        //        private AdapterImageNews adapterImageNews;
        List<AlbumEntity> list;
        AdapterImageNews adapter;
        private TextView tv_news_name, tv_content, tv_time, tv_like, tv_comment;
        private RoundImage img_avatar;
        private RecyclerView rcl_image;

        ViewHolder(View itemView) {
            super(itemView);
            list = new ArrayList<>();
            tv_news_name = itemView.findViewById(R.id.tv_news_name);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_like = itemView.findViewById(R.id.tv_like);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            rcl_image = itemView.findViewById(R.id.rcl_image);
            LinearLayoutManager manager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            rcl_image.setLayoutManager(manager);
//            adapter = new AdapterImageNews(itemView.getContext(), imageItemClick);
            rcl_image.setAdapter(adapter);
        }

        public void setData(List<AlbumEntity> list) {
            this.list = list;
            adapter.notifyDataSetChanged();
        }
    }
}

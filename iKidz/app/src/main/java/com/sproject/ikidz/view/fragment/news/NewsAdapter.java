package com.sproject.ikidz.view.fragment.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.AlbumEntity;
import com.sproject.ikidz.model.entity.NewsEntity;
import com.sproject.ikidz.sdk.Utils.RoundImage;
import com.sproject.ikidz.sdk.Utils.TextUtils;
import com.sproject.ikidz.sdk.Utils.WidgetUtils;
import com.sproject.ikidz.sdk.callback.ImageItemClick;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    List<NewsEntity> list;
    Context context;
    ImageItemClick imageItemClick;

    public NewsAdapter(List<NewsEntity> list, Context context, ImageItemClick imageItemClick) {
        this.list = list;
        this.context = context;
        this.imageItemClick = imageItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(list.get(position), imageItemClick);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_news_name, tv_content, tv_time, tv_like, tv_comment;
        private RoundImage img_avatar;
        private RecyclerView rcl_image;
        //        private AdapterImageNews adapterImageNews;
//        List<AlbumEntity> list;
        AdapterImageNews adapter;

        ViewHolder(View itemView) {
            super(itemView);
            tv_news_name = itemView.findViewById(R.id.tv_news_name);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_like = itemView.findViewById(R.id.tv_like);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            rcl_image = itemView.findViewById(R.id.rcl_image);
            LinearLayoutManager manager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            rcl_image.setLayoutManager(manager);
        }

        public void setData(NewsEntity entity, ImageItemClick click) {

            if (!TextUtils.isEmpty(entity.getFullName()))
                tv_news_name.setText(entity.getFullName());

            if (!TextUtils.isEmpty(entity.getAvatar()))
                WidgetUtils.setImageURL(img_avatar, entity.getAvatar(), R.mipmap.ic_launcher_round);


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
                tv_content.setText(entity.getTitle());

            tv_comment.setText(entity.getCountComment() + " bình luận");
            tv_like.setText(entity.getCountLike() + " lượt thích");

            adapter = new AdapterImageNews(entity.getAlbum(), itemView.getContext(), click);
            rcl_image.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}

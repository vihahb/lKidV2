package com.sproject.ikidz.view.activity.home.main_feature;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import com.sproject.ikidz.view.fragment.news.AdapterImageNews;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterNews extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<NewsEntity> newsList;
    Context context;
    LayoutInflater inflater;

    public AdapterNews(List<NewsEntity> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(newsList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_news_name, tv_content, tv_time, tv_like, tv_comment;
        private RoundImage img_avatar;
        private RecyclerView rcl_image;
        private AdapterImageNews adapterImageNews;
        StaggeredGridLayoutManager layoutManager;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_news_name = itemView.findViewById(R.id.tv_news_name);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_like = itemView.findViewById(R.id.tv_like);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            rcl_image = itemView.findViewById(R.id.rcl_image);
            adapterImageNews = new AdapterImageNews(itemView.getContext(), position -> {

            });
            layoutManager = new StaggeredGridLayoutManager(3, 0);
            rcl_image.setLayoutManager(layoutManager);
            rcl_image.setAdapter(adapterImageNews);
        }

        public void setData(NewsEntity data) {
            if (!TextUtils.isEmpty(data.getFullName()))
                tv_news_name.setText(data.getFullName());
            if (!TextUtils.isEmpty(data.getAvatar()))
                WidgetUtils.setImageURL(img_avatar, data.getAvatar(), R.mipmap.ic_launcher_round);


            if (!TextUtils.isEmpty(data.getPublishDate())){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(sdf.parse(data.getPublishDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                tv_time.setText(TextUtils.comparingTime(calendar.getTimeInMillis()));
            }

            if (!TextUtils.isEmpty(data.getTitle()))
                tv_content.setText(data.getTitle());

            if (data.getAlbum() != null && data.getAlbum().size() > 0) {
                adapterImageNews.setListData(data.getAlbum());
                rcl_image.setVisibility(View.VISIBLE);
            } else rcl_image.setVisibility(View.GONE);

            tv_like.setText(data.getCountLike() + " lượt thích");
            tv_comment.setText(data.getCountComment() + " bình luận");
        }
    }
}

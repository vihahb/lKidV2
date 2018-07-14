package com.snq.ikidz.teacher.view.fragment.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.snq.ikidz.teacher.R;
import com.snq.ikidz.teacher.model.entity.NewsEntity;
import com.snq.ikidz.teacher.sdk.Commons.Constants;
import com.snq.ikidz.teacher.sdk.Utils.RoundImage;
import com.snq.ikidz.teacher.sdk.Utils.TextUtils;
import com.snq.ikidz.teacher.sdk.Utils.WidgetUtils;
import com.snq.ikidz.teacher.sdk.callback.ImageItemClick;
import com.snq.ikidz.teacher.sdk.callback.ItemMoreActionGeneric;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    List<NewsEntity> list;
    Context context;
    ImageItemClick imageItemClick;
    ItemMoreActionGeneric<NewsEntity> listener;

    public NewsAdapter(List<NewsEntity> list, Context context, ImageItemClick imageItemClick, ItemMoreActionGeneric<NewsEntity> listener) {
        this.list = list;
        this.context = context;
        this.imageItemClick = imageItemClick;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position, list.get(position), imageItemClick);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //        private AdapterImageNews adapterImageNews;
//        List<AlbumEntity> list;
        AdapterImageNews adapter;
        private TextView tv_news_name, tv_content, tv_time, tv_like, tv_comment;
        private RoundImage img_avatar;
        private RecyclerView rcl_image;
        private LinearLayout ln_comment, ln_like;
        private ImageView img_like;

        ViewHolder(View itemView) {
            super(itemView);
            ln_like = itemView.findViewById(R.id.ln_like);
            ln_comment = itemView.findViewById(R.id.ln_comment);
            tv_news_name = itemView.findViewById(R.id.tv_news_name);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_like = itemView.findViewById(R.id.tv_like);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            img_like = itemView.findViewById(R.id.img_like);
            rcl_image = itemView.findViewById(R.id.rcl_image);
            LinearLayoutManager manager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            rcl_image.setLayoutManager(manager);
        }

        public void setData(int position, NewsEntity entity, ImageItemClick click) {

            if (entity.getIsLikePost() > 0){
                img_like.setImageResource(R.mipmap.ic_like_active);
            } else {
                img_like.setImageResource(R.mipmap.ic_like);
            }

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

            ln_like.setOnClickListener(view -> {
                listener.ItemClick(Constants.LIKE_POST, position, entity);
            });

            ln_comment.setOnClickListener(view -> {
                listener.ItemClick(Constants.COMMENT_POST, position, entity);
            });

        }
    }
}

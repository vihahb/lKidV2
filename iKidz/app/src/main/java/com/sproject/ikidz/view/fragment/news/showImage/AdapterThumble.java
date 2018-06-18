package com.sproject.ikidz.view.fragment.news.showImage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.iKidApplications;
import com.sproject.ikidz.model.entity.AlbumEntity;
import com.sproject.ikidz.sdk.Utils.RecyclerTabLayout;
import com.sproject.ikidz.sdk.Utils.TextUtils;
import com.sproject.ikidz.sdk.Utils.WidgetUtils;
import com.squareup.picasso.Callback;

import java.util.List;

public class AdapterThumble extends RecyclerTabLayout.Adapter<AdapterThumble.ViewHolder> {

    private static final String TAG = "AdapterThumble";

    int selectedPos = -1;
    List<AlbumEntity> list;
    Context context;
    ViewPager viewPager;

    public AdapterThumble(ViewPager viewPager, List<AlbumEntity> list, Context context) {
        super(viewPager);
        this.list = list;
        this.context = context;
        this.viewPager = viewPager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_thumb, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_thumb;

        public ViewHolder(View itemView) {
            super(itemView);
            img_thumb = itemView.findViewById(R.id.img_thumb);
        }

        public void setData(AlbumEntity entity, int position) {
            if (!TextUtils.isEmpty(entity.getImage()))
                WidgetUtils.setImageNotBaseURL(img_thumb, entity.getImage(), R.drawable.ic_load_image_error, new Callback() {
                    @Override
                    public void onSuccess() {
                        iKidApplications.log(TAG, "Load image success url: " + entity.toString());
                    }

                    @Override
                    public void onError() {
                        iKidApplications.log(TAG, "Load image error url: " + entity.toString());
                    }
                });

            itemView.setOnClickListener(view -> {
                viewPager.setCurrentItem(position);
                itemView.setSelected(true);
            });
        }
    }

}

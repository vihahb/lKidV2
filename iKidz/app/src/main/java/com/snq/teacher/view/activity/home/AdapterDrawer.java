package com.snq.teacher.view.activity.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snq.teacher.R;
import com.snq.teacher.model.entity.ItemDrawer;
import com.snq.teacher.sdk.Utils.TextUtils;
import com.snq.teacher.sdk.callback.ItemClickListener;

import java.util.List;

public class AdapterDrawer extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ItemDrawer> data;
    Context context;
    ItemClickListener views;

    public AdapterDrawer(List<ItemDrawer> data, Context context, ItemClickListener view) {
        this.data = data;
        this.context = context;
        this.views = view;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_drawer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView itemName;
        private ImageView iconItem;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.tv_item_name);
            iconItem = itemView.findViewById(R.id.itemIcon);
        }

        public void setData(ItemDrawer data) {
            if (!TextUtils.isEmpty(data.getName()))
                itemName.setText(data.name);

            if (data.getResource() != -1)
                iconItem.setImageResource(data.getResource());

            itemView.setOnClickListener(view -> views.ItemClick(data.getId()));
        }
    }
}

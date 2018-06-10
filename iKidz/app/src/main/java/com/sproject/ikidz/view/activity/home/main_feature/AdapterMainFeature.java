package com.sproject.ikidz.view.activity.home.main_feature;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.viewObject.Feature;
import com.sproject.ikidz.sdk.Utils.TextUtils;

import java.util.List;

public class AdapterMainFeature extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Feature> featureList;
    Context context;
    LayoutInflater inflater;

    public AdapterMainFeature(List<Feature> featureList, Context context) {
        this.featureList = featureList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_features, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  ViewHolder){
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(featureList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return featureList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_feature_name;
        private ImageView img_icon;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_feature_name = itemView.findViewById(R.id.tv_name);
            img_icon = itemView.findViewById(R.id.img_feature);
        }

        public void setData(Feature feature) {
            if (!TextUtils.isEmpty(feature.getName())){
                tv_feature_name.setText(feature.getName());
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //delegate onclick item
                }
            });
        }
    }
}

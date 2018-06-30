package com.sproject.ikidz.view.fragment.news.main_feature;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.viewObject.Feature;
import com.sproject.ikidz.sdk.Utils.TextUtils;
import com.sproject.ikidz.view.activity.absent.AbsentsActivity;
import com.sproject.ikidz.view.activity.attendance.in.AttendanceInActivity;
import com.sproject.ikidz.view.activity.attendance.outs.AttendanceOutActivity;
import com.sproject.ikidz.view.activity.drug.teacher.DrugActivity;
import com.sproject.ikidz.view.activity.learnOverTime.LearnOverTimeActivity;

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
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(featureList.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return featureList.size();
    }

    public void setShowFull(boolean b) {
        for (int i = 0; i < featureList.size(); i++) {
            featureList.get(i).setShowFull(b);
            notifyItemChanged(i);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_feature_name, tv_notify;
        private ImageView img_icon;
        private LinearLayout rootLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            rootLayout = itemView.findViewById(R.id.rootLayout);
            tv_feature_name = itemView.findViewById(R.id.tv_name);
            tv_notify = itemView.findViewById(R.id.tv_notify);
            img_icon = itemView.findViewById(R.id.img_feature);
        }

        public void setData(Feature feature, int position) {
            if (!TextUtils.isEmpty(feature.getName())) {
                tv_feature_name.setText(feature.getName());
            }

            if (feature.isShowFull()) {
                tv_feature_name.setTextColor(context.getResources().getColor(R.color.white_100));
                rootLayout.setBackgroundResource(feature.getResource());
            } else {
                rootLayout.setBackgroundResource(android.R.color.white);
                tv_feature_name.setTextColor(context.getResources().getColor(R.color.black_75));
            }
            if (feature.getMipmap() != -1)
                img_icon.setImageResource(feature.getMipmap());

            if (feature.getNotifyCount() > 0) {
                tv_notify.setText(feature.getNotifyCount() + "");
                tv_notify.setVisibility(View.VISIBLE);
            } else {
                tv_notify.setVisibility(View.GONE);
            }

            itemView.setOnClickListener(view -> {
                switch (position) {
                    case 0:
                        context.startActivity(new Intent(context, AbsentsActivity.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, AttendanceInActivity.class));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, DrugActivity.class));
                        break;
                    case 3:
                        context.startActivity(new Intent(context, LearnOverTimeActivity.class));
                        break;
                    case 4:
                        context.startActivity(new Intent(context, AttendanceOutActivity.class));
                        break;
                    case 5:
                        break;
                }
            });
        }
    }
}

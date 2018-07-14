package com.snq.teacher.view.activity.learnActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snq.teacher.R;
import com.snq.teacher.model.entity.LearnActivityEntity;
import com.snq.teacher.sdk.Commons.Constants;
import com.snq.teacher.sdk.Utils.TextUtils;
import com.snq.teacher.sdk.callback.ItemMoreActionGeneric;

import java.util.HashMap;
import java.util.List;

public class AdapterLearnActivity extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<LearnActivityEntity> data;
    Context context;
    ItemMoreActionGeneric<LearnActivityEntity> listener;
    private HashMap<Integer, Boolean> hashMap = new HashMap<>();

    public AdapterLearnActivity(List<LearnActivityEntity> data, Context context, ItemMoreActionGeneric<LearnActivityEntity> listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_learn_activity, parent, false));
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

        TextView tv_time, tv_content_morning, tv_content_after_noon;
        ImageView img_view, img_edit, img_more;
        private boolean isShow = false;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_content_morning = itemView.findViewById(R.id.tv_content_morning);
            tv_content_after_noon = itemView.findViewById(R.id.tv_content_after_noon);
            img_view = itemView.findViewById(R.id.img_view);
            img_edit = itemView.findViewById(R.id.img_edit);
            img_more = itemView.findViewById(R.id.img_more);
        }

        public void setData(LearnActivityEntity entity, int position) {
            if (!TextUtils.isEmpty(entity.getActivityDate())) {
                tv_time.setText(entity.getActivityDate());
            }
            if (!TextUtils.isEmpty(entity.getLearnMorning())) {
                tv_content_morning.setText("Sáng: " + entity.getLearnMorning());
            }

            if (!TextUtils.isEmpty(entity.getLearnMorning())) {
                tv_content_after_noon.setText("Chiều: " + entity.getLearnAfternoon());
            }


//            hashMap.put(position, isShow);

            img_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isShow) {
                        isShow = true;
                        setVisibleView(true);
                    } else {
                        isShow = false;
                        setVisibleView(false);
                    }
//                    hashMap.put(position, isShow);
//                    notifyDataSetChanged();
                }
            });

//            if (hashMap.get(position) != null) {
//                if (hashMap.get(entity.getId().toString())) {
//                    setVisibleView(true);
//                } else {
//                    setVisibleView(false);
//                }
//            }

            img_view.setOnClickListener(view -> listener.ItemClick(Constants.TYPE_VIEW, position, entity));
            img_edit.setOnClickListener(view -> listener.ItemClick(Constants.TYPE_EDIT, position, entity));
        }

        private void setVisibleView(boolean isShow) {
            if (isShow) {
                img_more.setImageResource(R.drawable.ic_more_vert_active);
                img_edit.setVisibility(View.VISIBLE);
                img_view.setVisibility(View.VISIBLE);
            } else {
                img_more.setImageResource(R.drawable.ic_more_vert);
                img_edit.setVisibility(View.INVISIBLE);
                img_view.setVisibility(View.INVISIBLE);
            }
        }
    }
}


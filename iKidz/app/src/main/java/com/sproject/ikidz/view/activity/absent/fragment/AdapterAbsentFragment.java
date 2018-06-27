package com.sproject.ikidz.view.activity.absent.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.AbsentEntity;
import com.sproject.ikidz.model.entity.StudentsEntity;
import com.sproject.ikidz.sdk.Utils.RoundImage;
import com.sproject.ikidz.sdk.Utils.TextUtils;
import com.sproject.ikidz.sdk.Utils.WidgetUtils;
import com.sproject.ikidz.sdk.callback.ItemClickListener;
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterAbsentFragment extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<AbsentEntity> list;
    Context context;
    ItemClickListener listener;


    public AdapterAbsentFragment(List<AbsentEntity> list, Context context, ItemClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_absents, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(list.get(position), position);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(@NotNull ArrayList<AbsentEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_number_row, tv_name, tv_des;
        RoundImage img_ava;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number_row = itemView.findViewById(R.id.tv_number_row);
            tv_name = itemView.findViewById(R.id.tv_absent_name);
            tv_des = itemView.findViewById(R.id.tv_absent_des);
            img_ava = itemView.findViewById(R.id.img_avatar);
        }

        public void setData(AbsentEntity data, int position) {
            if (!TextUtils.isEmpty(data.getFullNameStudent())) {
                tv_name.setText(data.getFullNameStudent());
            }

            if (data.getReceived() == 0)
                tv_name.setTypeface(tv_name.getTypeface(), Typeface.BOLD_ITALIC);
            else
                tv_name.setTypeface(tv_name.getTypeface(), Typeface.NORMAL);


            if (!TextUtils.isEmpty(data.getContent())) {
                tv_des.setText(data.getContent());
            }

            tv_number_row.setText("" + (position + 1));

            WidgetUtils.setImageURL(img_ava, data.getAvatarStudent(), R.mipmap.ic_launcher);

        }
    }
}



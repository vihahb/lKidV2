package com.sproject.ikidz.view.activity.drug.teacher.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.DrugEntity;
import com.sproject.ikidz.model.entity.LearnOverTimeEntity;
import com.sproject.ikidz.sdk.Utils.RoundImage;
import com.sproject.ikidz.sdk.Utils.TextUtils;
import com.sproject.ikidz.sdk.Utils.WidgetUtils;
import com.sproject.ikidz.sdk.callback.ItemClickListener;

import java.util.List;

public class AdapterDrugFragment extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<DrugEntity> list;
    Context context;
    ItemClickListener listener;


    public AdapterDrugFragment(List<DrugEntity> list, Context context, ItemClickListener listener) {
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

    public void filterList(List<DrugEntity> filterdNames) {
        this.list = filterdNames;
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

        public void setData(DrugEntity data, int position) {
            if (!TextUtils.isEmpty(data.getFullNameStudent())) {
                tv_name.setText(data.getFullNameStudent());
            }

            if (data.getReceived() == 0)
                tv_name.setTypeface(tv_name.getTypeface(), Typeface.BOLD_ITALIC);
            else
                tv_name.setTypeface(tv_name.getTypeface(), Typeface.NORMAL);

            if (!TextUtils.isEmpty(data.getNotes())) {
                tv_des.setText(data.getNotes());
            }

            tv_number_row.setText("" + (position + 1));

            WidgetUtils.setImageURL(img_ava, data.getAvatarStudent(), R.mipmap.ic_launcher);

        }
    }
}
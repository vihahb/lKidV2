package com.snq.ikidz.teacher.view.fragment.phonebook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snq.ikidz.teacher.R;
import com.snq.ikidz.teacher.model.entity.ClassPeopleEntity;
import com.snq.ikidz.teacher.sdk.Commons.Constants;
import com.snq.ikidz.teacher.sdk.Utils.RoundImage;
import com.snq.ikidz.teacher.sdk.Utils.TextUtils;
import com.snq.ikidz.teacher.sdk.Utils.WidgetUtils;
import com.snq.ikidz.teacher.sdk.callback.ItemMoreActionGeneric;

import java.util.List;

public class AdapterPhoneBook extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ClassPeopleEntity> data;
    Context context;
    ItemMoreActionGeneric<ClassPeopleEntity> listener;
    int TYPE_PEOPLE = 1, TYPE_DIVIDER = 0;
    private boolean isShow;

    public AdapterPhoneBook(List<ClassPeopleEntity> data, Context context, ItemMoreActionGeneric<ClassPeopleEntity> listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).isDivider()) {
            return TYPE_DIVIDER;
        } else {
            return TYPE_PEOPLE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_DIVIDER)
            return new DividerHolder(LayoutInflater.from(context).inflate(R.layout.item_divider, parent, false));
        else
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_phone_book, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(data.get(position), position);
        } else {
            DividerHolder holder1 = (DividerHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_des;
        RoundImage img_avatar;
        ImageView img_send_mesage, img_view, img_more;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_des = itemView.findViewById(R.id.tv_des);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            img_send_mesage = itemView.findViewById(R.id.img_send_message);
            img_view = itemView.findViewById(R.id.img_view);
            img_more = itemView.findViewById(R.id.img_more);
        }

        public void setData(ClassPeopleEntity entity, int position) {
            if (!TextUtils.isEmpty(entity.getFullName())) {
                tv_name.setText(entity.getFullName());
            }
            if (!TextUtils.isEmpty(entity.getRelationshipName())) {
                tv_des.setText(entity.getRelationshipName() + " của bé " + entity.getFirst_name_student());
            } else {
                if (entity.getGender().equals("0")) {
                    tv_des.setText("Cô giáo");
                } else {
                    tv_des.setText("Thầy giáo");
                }
            }
            if (!TextUtils.isEmpty(entity.getAvatar())) {
                WidgetUtils.setImageURL(img_avatar, entity.getAvatar(), R.mipmap.ic_launcher_round);
            }

            img_more.setOnClickListener(view -> {
                if (isShow) {
                    isShow = false;
                    showView(true);
                } else {
                    isShow = true;
                    showView(false);
                }
            });

            img_send_mesage.setOnClickListener(view -> listener.ItemClick(Constants.TYPE_EDIT, position, entity));

            img_view.setOnClickListener(view -> listener.ItemClick(Constants.TYPE_VIEW, position, entity));
        }

        private void showView(boolean b) {
            if (b) {
                img_send_mesage.setVisibility(View.VISIBLE);
                img_view.setVisibility(View.VISIBLE);
            } else {
                img_send_mesage.setVisibility(View.INVISIBLE);
                img_view.setVisibility(View.INVISIBLE);
            }
        }
    }

    class DividerHolder extends RecyclerView.ViewHolder {

        public DividerHolder(View itemView) {
            super(itemView);
        }
    }
}

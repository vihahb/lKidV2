package com.snq.ikidz.teacher.view.activity.attendance.in;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.snq.ikidz.teacher.R;
import com.snq.ikidz.teacher.model.entity.AttendanceIn;
import com.snq.ikidz.teacher.sdk.Commons.Constants;
import com.snq.ikidz.teacher.sdk.Utils.RoundImage;
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils;
import com.snq.ikidz.teacher.sdk.callback.ItemClickListenerGeneric;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class AdapterAttendanceIn extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private static final String TAG = "AdapterAttendanceIn";
    List<AttendanceIn> data;
    Context context;
    ItemClickListenerGeneric<AttendanceIn> listener;
    HashMap<Integer, Integer> stateHash;

    public AdapterAttendanceIn(List<AttendanceIn> data, Context context, ItemClickListenerGeneric<AttendanceIn> listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
        stateHash = new HashMap<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_attendance_in, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder: " + data.get(position));
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(data.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public void setAll(boolean in) {
        if (in) {
            for (int i = 0; i < data.size(); i++) {
                stateHash.put(i, 2);
            }
        } else {
            for (int i = 0; i < data.size(); i++) {
                stateHash.put(i, 0);
            }
        }
        notifyItemRangeChanged(0, data.size());
    }

    public void filterList(List<AttendanceIn> filterdNames) {
        this.data = filterdNames;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_number, tv_name;
        RoundImage im_avatar;
        RadioButton rdo_cophep, rdo_khongphep, rdo_denlop;
        RadioGroup rdo_group_in;

        public ViewHolder(View itemView) {
            super(itemView);
            im_avatar = itemView.findViewById(R.id.img_avatar);
            tv_name = itemView.findViewById(R.id.tv_attend_name);
            tv_number = itemView.findViewById(R.id.tv_number_row);
            rdo_cophep = itemView.findViewById(R.id.rdo_cophep);
            rdo_khongphep = itemView.findViewById(R.id.rdo_khongphep);
            rdo_denlop = itemView.findViewById(R.id.rdo_denlop);
            rdo_group_in = itemView.findViewById(R.id.rdo_group_in);
        }

        public void setData(AttendanceIn entity, int position) {
            tv_number.setText(position + 1 + "");
            tv_name.setText(entity.getFullName());

            String urls = SharedUtils.getInstance().getStringValue(Constants.BASE_URL) + "/" + entity.getAvatar();
            Picasso.with(context)
                    .load(urls)
                    .noPlaceholder()
                    .error(R.mipmap.ic_launcher)
                    .resize(150, 150)
                    .centerCrop()
                    .into(im_avatar);

            if (entity.getChecked() != null) {
                switch (entity.getChecked()) {
                    case 0:
                        stateHash.put(position, 0);
                        break;
                    case 1:
                        stateHash.put(position, 1);
                        break;
                    case 2:
                        stateHash.put(position, 2);
                        break;
                    default:
                        stateHash.put(position, 3);
                }
            } else {
                stateHash.put(position, -1);
            }

            if (stateHash.get(position) != null) {
                switch (stateHash.get(position)) {
                    case 0:
                        rdo_group_in.check(R.id.rdo_khongphep);
                        break;
                    case 1:
                        rdo_group_in.check(R.id.rdo_cophep);
                        break;
                    case 2:
                        rdo_group_in.check(R.id.rdo_denlop);
                        break;
                    default:
                        rdo_group_in.clearCheck();
                }
                setRadioCorlor(stateHash.get(position));
            }
            itemView.setOnClickListener(view -> listener.ItemClick(position, entity));
        }

        void setRadioCorlor(int type) {
            switch (type) {
                case -1:
                    rdo_khongphep.setTextColor(context.getResources().getColor(R.color.normal_text_dario));
                    rdo_cophep.setTextColor(context.getResources().getColor(R.color.normal_text_dario));
                    rdo_denlop.setTextColor(context.getResources().getColor(R.color.normal_text_dario));
                    break;
                case 0:
                    rdo_khongphep.setTextColor(context.getResources().getColor(R.color.red));
                    rdo_cophep.setTextColor(context.getResources().getColor(R.color.normal_text_dario));
                    rdo_denlop.setTextColor(context.getResources().getColor(R.color.normal_text_dario));
                    break;
                case 1:
                    rdo_khongphep.setTextColor(context.getResources().getColor(R.color.normal_text_dario));
                    rdo_cophep.setTextColor(context.getResources().getColor(R.color.yellow));
                    rdo_denlop.setTextColor(context.getResources().getColor(R.color.normal_text_dario));
                    break;
                case 2:
                    rdo_khongphep.setTextColor(context.getResources().getColor(R.color.normal_text_dario));
                    rdo_cophep.setTextColor(context.getResources().getColor(R.color.normal_text_dario));
                    rdo_denlop.setTextColor(context.getResources().getColor(R.color.green_yet));
                    break;
                case 3:
                    rdo_khongphep.setTextColor(context.getResources().getColor(R.color.normal_text_dario));
                    rdo_cophep.setTextColor(context.getResources().getColor(R.color.normal_text_dario));
                    rdo_denlop.setTextColor(context.getResources().getColor(R.color.normal_text_dario));
                    break;
            }
        }
    }
}


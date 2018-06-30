package com.sproject.ikidz.view.activity.attendance.outs;

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

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.AttendanceOut;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.RoundImage;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

//
public class AdapterAttendanceOut extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private static final String TAG = "AdapterAttendanceIn";
    List<AttendanceOut> data;
    Context context;

    public AdapterAttendanceOut(List<AttendanceOut> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_attendance_out, parent, false));
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
                data.get(i).setChecked(String.valueOf(3));
            }
        } else {
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setChecked(String.valueOf(2));
            }
        }
        notifyItemRangeChanged(0, data.size());
    }

    public void filterList(List<AttendanceOut> filterdNames) {
        this.data = filterdNames;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_number, tv_name;
        RoundImage im_avatar;
        RadioButton rdo_chuave, rdo_dave;
        RadioGroup rdo_group_in;

        public ViewHolder(View itemView) {
            super(itemView);
            im_avatar = itemView.findViewById(R.id.img_avatar);
            tv_name = itemView.findViewById(R.id.tv_attend_name);
            tv_number = itemView.findViewById(R.id.tv_number_row);
            rdo_chuave = itemView.findViewById(R.id.rdo_chuave);
            rdo_dave = itemView.findViewById(R.id.rdo_dave);
            rdo_group_in = itemView.findViewById(R.id.rdo_group_in);
        }

        public void setData(AttendanceOut entity, int position) {
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
                switch (Integer.parseInt(entity.getChecked())) {
                    case 2:
                        rdo_group_in.check(R.id.rdo_chuave);
                        break;
                    case 3:
                        rdo_group_in.check(R.id.rdo_dave);
                        break;
                }
            }


        }
    }
}
package com.snq.teacher.view.activity.schoolProfile.info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.snq.teacher.R;
import com.snq.teacher.model.entity.UpToClassEntity;

import java.util.List;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 11:14 PM
 * Email: vihahb@gmail.com
 */
public class AdapterSpinnerUpto extends BaseAdapter {

    List<UpToClassEntity> StringList;
    Context context;

    public AdapterSpinnerUpto(Context context, List<UpToClassEntity> StringList) {
        this.context = context;
        this.StringList = StringList;
    }

    @Override
    public int getCount() {
        return StringList.size();
    }

    @Override
    public Object getItem(int position) {
        return StringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_title_profile, parent, false);
        TextView tv_menu = view.findViewById(R.id.tv_state);
        tv_menu.setText(StringList.get(position).getTitle());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_title_profile, parent, false);
        TextView tv_menu = view.findViewById(R.id.tv_state);
        view.setBackgroundResource(R.color.white_100);
        tv_menu.setText(StringList.get(position).getTitle());
        ImageView img_down = view.findViewById(R.id.img_down);
        img_down.setVisibility(View.INVISIBLE);
        return view;
    }
}
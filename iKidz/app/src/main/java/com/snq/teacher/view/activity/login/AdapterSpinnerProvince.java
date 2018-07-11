package com.snq.teacher.view.activity.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.snq.teacher.R;
import com.snq.teacher.model.entity.ProvinceOrDistrict;

import java.util.List;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 11:14 PM
 * Email: vihahb@gmail.com
 */
public class AdapterSpinnerProvince extends BaseAdapter {

    List<ProvinceOrDistrict> provincesList;
    Context context;

    public AdapterSpinnerProvince(Context context, List<ProvinceOrDistrict> provincesList) {
        this.context = context;
        this.provincesList = provincesList;
    }

    @Override
    public int getCount() {
        return provincesList.size();
    }

    @Override
    public Object getItem(int position) {
        return provincesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return provincesList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_province, parent, false);
        TextView tv_provinceName = view.findViewById(R.id.tv_province_name);
        tv_provinceName.setText(provincesList.get(position).getTitle());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_province_dropdown_view, parent, false);
        TextView tv_provinceName = view.findViewById(R.id.tv_province_name);
        tv_provinceName.setText(provincesList.get(position).getTitle());
        return view;
    }
}

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
public class AdapterSpinnerDistrict extends BaseAdapter {

    List<ProvinceOrDistrict> districtList;
    Context context;

    public AdapterSpinnerDistrict(Context context, List<ProvinceOrDistrict> districtList) {
        this.context = context;
        this.districtList = districtList;
    }

    @Override
    public int getCount() {
        return districtList.size();
    }

    @Override
    public Object getItem(int position) {
        return districtList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return districtList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_district, parent, false);
        TextView tv_provinceName = view.findViewById(R.id.tv_district_name);
        tv_provinceName.setText(districtList.get(position).getTitle());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_province_dropdown_view, parent, false);
        TextView tv_provinceName = view.findViewById(R.id.tv_province_name);
        tv_provinceName.setText(districtList.get(position).getTitle());
        return view;
    }
}
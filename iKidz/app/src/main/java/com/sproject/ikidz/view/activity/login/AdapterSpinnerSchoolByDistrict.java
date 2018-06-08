package com.sproject.ikidz.view.activity.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.model.entity.SchoolByDistrict;

import java.util.List;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/9/2018
 * Time: 12:41 AM
 * Email: vihahb@gmail.com
 */
public class AdapterSpinnerSchoolByDistrict extends BaseAdapter {

    List<SchoolByDistrict> data;
    Context context;

    public AdapterSpinnerSchoolByDistrict(Context context, List<SchoolByDistrict> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_school, parent, false);
        TextView tv_schoolName = view.findViewById(R.id.tv_school_name);
        tv_schoolName.setText(data.get(position).getNameSchool());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_school_dropdowm_view, parent, false);
        TextView tv_schoolName = view.findViewById(R.id.tv_school_name);
        tv_schoolName.setText(data.get(position).getNameSchool());
        return view;
    }
}

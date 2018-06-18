package com.sproject.ikidz.view.activity.editProfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sproject.ikidz.R;

import java.util.List;

public class AdapterGender extends BaseAdapter {

    List<String> stringList;
    Context context;

    public AdapterGender(List<String> stringList, Context context) {
        this.stringList = stringList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int i) {
        return stringList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item_gender, viewGroup, false);
        TextView tv_gender = view.findViewById(R.id.tv_gender);
        tv_gender.setText(stringList.get(position));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dropdown_gender, parent, false);
        TextView tv_gender = convertView.findViewById(R.id.tv_gender);
        tv_gender.setText(stringList.get(position));
        return convertView;
    }
}

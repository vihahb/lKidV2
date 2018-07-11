package com.snq.ikidz.teacher.view.activity.eatActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.snq.ikidz.teacher.R;

import java.util.List;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 11:14 PM
 * Email: vihahb@gmail.com
 */
public class AdapterSpinnerEatState extends BaseAdapter {

    List<String> menuList;
    Context context;

    public AdapterSpinnerEatState(Context context, List<String> provincesList) {
        this.context = context;
        this.menuList = provincesList;
    }

    @Override
    public int getCount() {
        return menuList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_eat_state, parent, false);
        TextView tv_menu = view.findViewById(R.id.tv_state);
        tv_menu.setText(menuList.get(position));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_eat_state, parent, false);
        TextView tv_menu = view.findViewById(R.id.tv_state);
        view.setBackgroundResource(R.color.white_100);
        tv_menu.setText(menuList.get(position));
        ImageView img_down = view.findViewById(R.id.img_down);
        img_down.setVisibility(View.INVISIBLE);
        return view;
    }
}

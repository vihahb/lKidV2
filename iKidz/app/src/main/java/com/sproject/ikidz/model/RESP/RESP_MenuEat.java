package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.MenuEatEntity;

import java.util.List;

public class RESP_MenuEat extends RESP_Basic {
    @Expose
    private List<MenuEatEntity> data;

    public List<MenuEatEntity> getData() {
        return data;
    }

    public void setData(List<MenuEatEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_MenuEat{" +
                "data=" + data +
                '}';
    }
}

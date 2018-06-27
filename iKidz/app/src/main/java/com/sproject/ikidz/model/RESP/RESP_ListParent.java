package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.ParentEntity;

import java.util.List;

public class RESP_ListParent extends RESP_Basic {
    @Expose
    private List<ParentEntity> data;

    public List<ParentEntity> getData() {
        return data;
    }

    public void setData(List<ParentEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_ListParent{" +
                "data=" + data +
                '}';
    }
}

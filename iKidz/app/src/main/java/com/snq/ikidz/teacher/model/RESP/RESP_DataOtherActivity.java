package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.model.entity.OtherActivitysEntity;

import java.util.List;

public class RESP_DataOtherActivity extends RESP_Basic {
    @Expose
    private List<OtherActivitysEntity> data;

    public List<OtherActivitysEntity> getData() {
        return data;
    }

    public void setData(List<OtherActivitysEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_DataOtherActivity{" +
                "data=" + data +
                '}';
    }
}

package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.model.entity.DataEatEntity;

public class RESP_DataEat extends RESP_Basic {
    @Expose
    private DataEatEntity data;

    public DataEatEntity getData() {
        return data;
    }

    public void setData(DataEatEntity data) {
        this.data = data;
    }
}

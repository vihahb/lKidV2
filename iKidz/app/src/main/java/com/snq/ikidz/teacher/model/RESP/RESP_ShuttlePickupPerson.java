package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.model.entity.ShuttlepickupPersonEntity;

import java.util.List;

public class RESP_ShuttlePickupPerson extends RESP_Basic {

    @Expose
    private List<ShuttlepickupPersonEntity> data;

    public List<ShuttlepickupPersonEntity> getData() {
        return data;
    }

    public void setData(List<ShuttlepickupPersonEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_ShuttlePickupPerson{" +
                "data=" + data +
                '}';
    }
}

package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.model.entity.ShuttleBusEntity;

import java.util.List;

public class RESP_ShuttlesBus extends RESP_Basic {

    @Expose
    private List<ShuttleBusEntity> data;

    public List<ShuttleBusEntity> getData() {
        return data;
    }

    public void setData(List<ShuttleBusEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_ShuttlesBus{" +
                "data=" + data +
                '}';
    }
}

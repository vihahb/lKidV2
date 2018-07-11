package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.model.entity.RespondStatus;

public class RESP_Status extends RESP_Basic {
    @Expose
    private RespondStatus data;

    public RespondStatus getData() {
        return data;
    }

    public void setData(RespondStatus data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_Status{" +
                "data=" + data +
                '}';
    }
}

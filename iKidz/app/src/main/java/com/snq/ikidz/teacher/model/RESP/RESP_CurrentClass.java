package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.model.entity.DataStudent;

public class RESP_CurrentClass extends RESP_Basic {
    @Expose
    private DataStudent data;

    public DataStudent getData() {
        return data;
    }

    public void setData(DataStudent data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_CurrentClass{" +
                "data=" + data +
                '}';
    }
}

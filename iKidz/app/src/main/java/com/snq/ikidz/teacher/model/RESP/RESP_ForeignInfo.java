package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.model.entity.DataForeignInfo;

public class RESP_ForeignInfo extends RESP_Basic {
    @Expose
    private DataForeignInfo data;

    public DataForeignInfo getData() {
        return data;
    }

    public void setData(DataForeignInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_ForeignInfo{" +
                "data=" + data +
                '}';
    }
}

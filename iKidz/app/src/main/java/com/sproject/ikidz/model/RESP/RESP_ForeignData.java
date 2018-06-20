package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.DataForeignActivity;

public class RESP_ForeignData extends RESP_Basic {

    @Expose
    private DataForeignActivity data;

    public DataForeignActivity getData() {
        return data;
    }

    public void setData(DataForeignActivity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_ForeignData{" +
                "data=" + data +
                '}';
    }
}

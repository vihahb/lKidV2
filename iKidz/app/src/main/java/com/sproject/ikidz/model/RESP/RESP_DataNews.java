package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.DataNews;

public class RESP_DataNews extends RESP_Basic {
    @Expose
    private DataNews data;

    public DataNews getData() {
        return data;
    }

    public void setData(DataNews data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_DataNews{" +
                "data=" + data +
                '}';
    }
}

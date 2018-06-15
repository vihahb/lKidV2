package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.DataCountNotify;

public class RESP_CountNotify extends RESP_Basic {
    @Expose
    private DataCountNotify data;

    public DataCountNotify getData() {
        return data;
    }

    public void setData(DataCountNotify data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_CountNotify{" +
                "data=" + data +
                '}';
    }
}

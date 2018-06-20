package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.DataDrug;

public class RESP_GetDrug extends RESP_Basic {
    @Expose
    private DataDrug data;

    public DataDrug getData() {
        return data;
    }

    public void setData(DataDrug data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_GetDrug{" +
                "data=" + data +
                '}';
    }
}

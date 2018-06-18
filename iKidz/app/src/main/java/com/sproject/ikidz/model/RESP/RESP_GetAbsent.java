package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.AbsentEntity;
import com.sproject.ikidz.model.entity.DataAbsent;

import java.util.List;

public class RESP_GetAbsent extends RESP_Basic {
    @Expose
    private DataAbsent data;

    public DataAbsent getData() {
        return data;
    }

    public void setData(DataAbsent data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_GetAbsent{" +
                "data=" + data +
                '}';
    }
}

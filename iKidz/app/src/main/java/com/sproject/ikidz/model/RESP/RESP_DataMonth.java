package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.DataWeek;

import java.util.List;

public class RESP_DataMonth extends RESP_Basic {
    @Expose
    private List<DataWeek> data;

    public List<DataWeek> getData() {
        return data;
    }

    public void setData(List<DataWeek> data) {
        this.data = data;
    }
}

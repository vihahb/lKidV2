package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.model.entity.DataWeek;

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

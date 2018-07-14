package com.snq.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.teacher.model.entity.DataAbsent;

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

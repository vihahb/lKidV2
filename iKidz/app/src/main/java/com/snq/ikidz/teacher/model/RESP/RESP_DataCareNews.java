package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.model.entity.DataCareNewsEntity;

public class RESP_DataCareNews extends RESP_Basic {
    @Expose
    private DataCareNewsEntity data;

    public DataCareNewsEntity getData() {
        return data;
    }

    public void setData(DataCareNewsEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_DataCareNews{" +
                "data=" + data +
                '}';
    }
}

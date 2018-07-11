package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.model.entity.DataProfileEntity;

public class RESP_DataProfile extends RESP_Basic {
    @Expose
    private DataProfileEntity data;

    public DataProfileEntity getData() {
        return data;
    }

    public void setData(DataProfileEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_DataProfile{" +
                "data=" + data +
                '}';
    }
}

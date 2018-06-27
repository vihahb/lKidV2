package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.DataStudentsEntity;

public class RESP_StudentEntity extends RESP_Basic {
    @Expose
    private DataStudentsEntity data;

    public DataStudentsEntity getData() {
        return data;
    }

    public void setData(DataStudentsEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_StudentEntity{" +
                "data=" + data +
                '}';
    }
}

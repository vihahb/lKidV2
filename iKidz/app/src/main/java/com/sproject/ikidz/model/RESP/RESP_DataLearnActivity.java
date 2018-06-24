package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.DataLearnActivityEntity;

public class RESP_DataLearnActivity extends RESP_Basic {

    @Expose
    private DataLearnActivityEntity data;

    public DataLearnActivityEntity getData() {
        return data;
    }

    public void setData(DataLearnActivityEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_DataLearnActivity{" +
                "data=" + data +
                '}';
    }
}

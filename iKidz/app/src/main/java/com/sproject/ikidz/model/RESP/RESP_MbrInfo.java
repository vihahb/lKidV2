package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.MbrInfoEntity;

public class RESP_MbrInfo extends RESP_Basic {
    @Expose
    private MbrInfoEntity data;

    public MbrInfoEntity getData() {
        return data;
    }

    public void setData(MbrInfoEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_MbrInfo{" +
                "data=" + data +
                '}';
    }
}

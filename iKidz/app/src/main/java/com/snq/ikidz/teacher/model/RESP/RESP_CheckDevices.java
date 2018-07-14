package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;

public class RESP_CheckDevices extends RESP_Basic {
    @Expose
    private RESPCheckDevices data;

    public RESPCheckDevices getData() {
        return data;
    }

    public void setData(RESPCheckDevices data) {
        this.data = data;
    }
}

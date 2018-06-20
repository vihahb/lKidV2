package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.AttendanceIn;
import com.sproject.ikidz.model.entity.AttendanceOut;

import java.util.List;

public class RESP_AttendanceIn extends RESP_Basic {

    @Expose
    private List<AttendanceIn> data;

    public List<AttendanceIn> getData() {
        return data;
    }

    public void setData(List<AttendanceIn> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_AttendanceIn{" +
                "data=" + data +
                '}';
    }
}

package com.snq.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.teacher.model.entity.AttendanceOut;

import java.util.List;

public class RESP_AttendanceOut extends RESP_Basic {

    @Expose
    private List<AttendanceOut> data;

    public List<AttendanceOut> getData() {
        return data;
    }

    public void setData(List<AttendanceOut> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_AttendanceIn{" +
                "data=" + data +
                '}';
    }
}
package com.snq.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.teacher.model.entity.AttendanceIn;

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

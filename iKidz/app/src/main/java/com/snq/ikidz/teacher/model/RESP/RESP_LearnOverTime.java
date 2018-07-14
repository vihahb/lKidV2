package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.model.entity.DataLearnOverTime;

public class RESP_LearnOverTime extends RESP_Basic {
    @Expose
    private DataLearnOverTime data;

    public DataLearnOverTime getData() {
        return data;
    }

    public void setData(DataLearnOverTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_LearnOverTime{" +
                "data=" + data +
                '}';
    }
}

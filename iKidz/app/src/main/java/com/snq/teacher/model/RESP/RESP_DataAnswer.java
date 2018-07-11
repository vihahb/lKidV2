package com.snq.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.teacher.model.entity.RespondAnswer;

public class RESP_DataAnswer extends RESP_Basic {
    @Expose
    private RespondAnswer data;

    public RespondAnswer getData() {
        return data;
    }

    public void setData(RespondAnswer data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_DataAnswer{" +
                "data=" + data +
                '}';
    }
}

package com.snq.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.teacher.model.entity.EatTicketEntity;

import java.util.List;

public class RESP_LogToEat extends RESP_Basic {
    @Expose
    private List<EatTicketEntity> data;

    public List<EatTicketEntity> getData() {
        return data;
    }

    public void setData(List<EatTicketEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_LogToEat{" +
                "data=" + data +
                '}';
    }
}

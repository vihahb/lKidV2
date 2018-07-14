package com.snq.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.teacher.model.entity.DataEatTicketEntity;

public class RESP_DataEatTicket extends RESP_Basic {

    @Expose
    private DataEatTicketEntity data;

    public DataEatTicketEntity getData() {
        return data;
    }

    public void setData(DataEatTicketEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_DataEatTicket{" +
                "data=" + data +
                '}';
    }
}

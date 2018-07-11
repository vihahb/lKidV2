package com.snq.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.teacher.model.entity.SleepEntity;

import java.util.List;

public class RESP_DataSleep extends RESP_Basic {
    @Expose
    private List<SleepEntity> data;

    public List<SleepEntity> getData() {
        return data;
    }

    public void setData(List<SleepEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_DataSleep{" +
                "data=" + data +
                '}';
    }
}

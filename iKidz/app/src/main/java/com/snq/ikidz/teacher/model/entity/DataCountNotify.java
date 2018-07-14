package com.snq.ikidz.teacher.model.entity;

import com.google.gson.annotations.Expose;

public class DataCountNotify {
    @Expose
    private CountNotify data;

    public DataCountNotify(CountNotify data) {
        this.data = data;
    }

    public CountNotify getData() {
        return data;
    }

    public void setData(CountNotify data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataCountNotify{" +
                "data=" + data +
                '}';
    }
}

package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;

import java.util.List;

public class RESP_SampleReview extends RESP_Basic {
    @Expose
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_SampleReview{" +
                "data=" + data +
                '}';
    }
}

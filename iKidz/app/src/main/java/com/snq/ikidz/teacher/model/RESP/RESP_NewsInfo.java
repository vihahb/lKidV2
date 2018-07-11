package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.model.entity.NewsInfo;

public class RESP_NewsInfo extends RESP_Basic {
    @Expose
    private NewsInfo data;

    public NewsInfo getData() {
        return data;
    }

    public void setData(NewsInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_NewsInfo{" +
                "data=" + data +
                '}';
    }
}

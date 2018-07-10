package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.IsLikePost;

public class RESP_like extends RESP_Basic {
    @Expose
    private IsLikePost data;

    public IsLikePost getData() {
        return data;
    }

    public void setData(IsLikePost data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_like{" +
                "data=" + data +
                '}';
    }
}

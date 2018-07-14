package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.model.entity.CommentLearnEntity;

import java.util.List;

public class RESP_CommentLearn extends RESP_Basic {
    @Expose
    private List<CommentLearnEntity> data;

    public List<CommentLearnEntity> getData() {
        return data;
    }

    public void setData(List<CommentLearnEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_CommentLearn{" +
                "data=" + data +
                '}';
    }
}

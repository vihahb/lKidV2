package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.DataCommentEntity;

import java.util.List;

public class RESP_GetComment extends RESP_Basic {

    @Expose
    private List<DataCommentEntity> data;

    public List<DataCommentEntity> getData() {
        return data;
    }

    public void setData(List<DataCommentEntity> data) {
        this.data = data;
    }
}

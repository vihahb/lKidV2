package com.snq.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.teacher.model.entity.DataCommentEntity;

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

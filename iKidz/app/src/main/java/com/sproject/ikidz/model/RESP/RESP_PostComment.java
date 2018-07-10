package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.RespondCommentEntity;

public class RESP_PostComment extends RESP_Basic{
    @Expose
    private RespondCommentEntity data;

    public RespondCommentEntity getData() {
        return data;
    }

    public void setData(RespondCommentEntity data) {
        this.data = data;
    }
}

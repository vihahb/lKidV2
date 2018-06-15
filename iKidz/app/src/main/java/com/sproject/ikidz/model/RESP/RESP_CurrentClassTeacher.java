package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.ClassCurrentTeacher;

public class RESP_CurrentClassTeacher extends RESP_Basic {

    @Expose
    private ClassCurrentTeacher data;

    public ClassCurrentTeacher getData() {
        return data;
    }

    public void setData(ClassCurrentTeacher data) {
        this.data = data;
    }
}

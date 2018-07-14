package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.model.entity.ClassCurrentTeacher;

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

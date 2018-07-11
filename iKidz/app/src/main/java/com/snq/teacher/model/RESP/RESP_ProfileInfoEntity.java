package com.snq.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.teacher.model.entity.ProfileInfoEntify;

public class RESP_ProfileInfoEntity extends RESP_Basic {
    @Expose
    private ProfileInfoEntify data;

    public ProfileInfoEntify getData() {
        return data;
    }

    public void setData(ProfileInfoEntify data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_ProfileInfoEntity{" +
                "data=" + data +
                '}';
    }
}

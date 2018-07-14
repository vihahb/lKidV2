package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.model.entity.RespondResultCampaign;

public class RESP_ResultValue extends RESP_Basic {
    @Expose
    private RespondResultCampaign data;

    public RespondResultCampaign getData() {
        return data;
    }

    public void setData(RespondResultCampaign data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_ResultValue{" +
                "data=" + data +
                '}';
    }
}

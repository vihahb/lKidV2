package com.snq.ikidz.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.model.entity.InfoCampaignEntity;

public class RESP_InfoCampagn extends RESP_Basic {
    @Expose
    private InfoCampaignEntity data;

    public InfoCampaignEntity getData() {
        return data;
    }

    public void setData(InfoCampaignEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_InfoCampagn{" +
                "data=" + data +
                '}';
    }
}

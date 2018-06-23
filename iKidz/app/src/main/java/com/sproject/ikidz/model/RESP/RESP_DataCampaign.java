package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.DataCampainEntity;

public class RESP_DataCampaign extends RESP_Basic {
    @Expose
    private DataCampainEntity data;

    public DataCampainEntity getData() {
        return data;
    }

    public void setData(DataCampainEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_DataCampaign{" +
                "data=" + data +
                '}';
    }
}

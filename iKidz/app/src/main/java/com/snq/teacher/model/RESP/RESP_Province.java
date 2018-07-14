package com.snq.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.teacher.model.entity.ProvinceOrDistrict;

import java.util.List;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 10:17 PM
 * Email: vihahb@gmail.com
 */
public class RESP_Province extends RESP_Basic {
    @Expose
    private List<ProvinceOrDistrict> data;

    public List<ProvinceOrDistrict> getData() {
        return data;
    }

    public void setData(List<ProvinceOrDistrict> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_Province{" +
                "data=" + data +
                '}';
    }
}
